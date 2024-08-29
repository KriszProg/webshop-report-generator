package hu.otpmobil.validator;

import hu.otpmobil.model.LineError;
import hu.otpmobil.model.FieldName;
import hu.otpmobil.model.PaymentType;
import hu.otpmobil.model.UniqueId;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static hu.otpmobil.config.ApplicationConstants.REQUIRED_AMOUNT_OF_PAYMENT_DATA_PER_LINE;

public class PaymentDataValidator extends AbstractValidator {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PaymentDataValidator.class);

    public PaymentDataValidator() {
        super(LOGGER);
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public boolean isPaymentDataValid(String fileName, Integer lineNumber, String line, Separator separator) {
        if (!isPaymentDataSyntacticallyValid(fileName, lineNumber, line, separator, REQUIRED_AMOUNT_OF_PAYMENT_DATA_PER_LINE)) {
            return false;
        }

        List<String> lineErrors = validateLineDetailsAndCollectLineError(line, separator);
        if (!lineErrors.isEmpty()) {
            LineError lineError = new LineError()
                    .fileName(fileName)
                    .lineNumber(lineNumber)
                    .lineContent(line)
                    .errors(lineErrors);
            logLineError(lineError);
            return false;
        }

        return true;
    }

    public boolean isUniqueIdExists(String fileName, Integer lineNumber, String line, UniqueId uniqueId, List<UniqueId> uniqueIdList) {
        if (!isUniqueIdExists(uniqueId, uniqueIdList)) {
            LineError lineError = new LineError().fileName(fileName).lineNumber(lineNumber).lineContent(line);
            lineError.addError(Message.PAYMENT_DATA_CANNOT_LINK_TO_CUSTOMER.getMessage());
            logLineError(lineError);
            return false;
        }
        return true;
    }

    private List<String> validateLineDetailsAndCollectLineError(String line, Separator separator) {
        List<String> lineErrors = new ArrayList<>();
        String[] data = line.split(separator.getSeparatorRegex());

        validateWebShopIdAndAddError(data[0], lineErrors);
        validateCustomerIdAndAddError(data[1], lineErrors);
        PaymentType paymentType = getValidatedPaymentTypeOrNull(data[2], lineErrors);
        validateAmountAndAddError(data[3], lineErrors);
        validateBankAccountNumberAndAddError(paymentType, data[4], lineErrors);
        validateCardNumberAndAddError(paymentType, data[5], lineErrors);
        validateDateAndAddError(data[6], lineErrors);

        return lineErrors;
    }

    private void validateWebShopIdAndAddError(String webShopId, List<String> lineErrors) {
        if (webShopId.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.WEB_SHOP_ID.getName()));
        }
    }

    private void validateCustomerIdAndAddError(String customerId, List<String> lineErrors) {
        if (customerId.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.CUSTOMER_ID.getName()));
        }
    }

    private PaymentType getValidatedPaymentTypeOrNull(String paymentTypeString, List<String> lineErrors) {
        if (paymentTypeString.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.PAYMENT_TYPE.getName()));
            return null;
        }

        PaymentType paymentType = PaymentType.getPaymentTypeByName(paymentTypeString);
        if (paymentType == null) {
            lineErrors.add(Message.INVALID_PAYMENT_TYPE.getMessage());
        }
        return paymentType;
    }

    private void validateAmountAndAddError(String amountString, List<String> lineErrors) {
        if (amountString.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.AMOUNT.getName()));
        } else {
            try {
                Integer amount = Integer.parseInt(amountString.trim());
                if (amount <= 0) {
                    lineErrors.add(Message.INVALID_AMOUNT.getMessage());
                }
            } catch (NumberFormatException e) {
                lineErrors.add(Message.INVALID_AMOUNT_FORMAT.getMessage());
            }
        }
    }

    private void validateBankAccountNumberAndAddError(PaymentType paymentType, String bankAccountNumber, List<String> lineErrors) {
        if (paymentType == PaymentType.TRANSFER) {
            if (bankAccountNumber.isEmpty()) {
                lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.BANK_ACCOUNT_NUMBER.getName()));
            }
        }
    }

    private void validateCardNumberAndAddError(PaymentType paymentType, String cardNumber, List<String> lineErrors) {
        if (paymentType == PaymentType.CARD) {
            if (cardNumber.isEmpty()) {
                lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.CARD_NUMBER.getName()));
            } else if (!isCardNumberValid(cardNumber)) {
                lineErrors.add(Message.INVALID_CARD_NUMBER.getMessage());
            }
        }
    }

    private boolean isCardNumberValid(String cardNumber) {
        int sum = 0;
        boolean isSecond = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int number = Integer.parseInt(cardNumber.substring(i, i + 1));

            if (isSecond) {
                number *= 2;
                if (number > 9) {
                    number -= 9;
                }
            }

            sum += number;
            isSecond = !isSecond;
        }

        return (sum % 10 == 0);
    }

    private void validateDateAndAddError(String dateString, List<String> lineErrors) {
        if (dateString.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.DATE.getName()));
        } else {
            try {
                LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                lineErrors.add(Message.INVALID_DATE_FORMAT.getMessage());
            }
        }
    }

}
