package hu.otpmobil.validator;

import hu.otpmobil.model.FieldName;
import hu.otpmobil.model.PaymentType;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static hu.otpmobil.config.ApplicationConstants.DATE_TIME_FORMATTER;

public class PaymentDataValidator extends AbstractValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDataValidator.class);

    public PaymentDataValidator() {
        super(LOGGER);
    }

    @Override
    protected List<String> validateLineDetailsAndCollectLineErrors(String line, Separator separator) {
        List<String> lineErrors = new ArrayList<>();
        String[] data = line.split(separator.getSeparatorRegex());

        validateDataIsNotEmptyAndAddError(data[0], FieldName.WEB_SHOP_ID, lineErrors);
        validateDataIsNotEmptyAndAddError(data[1], FieldName.CUSTOMER_ID, lineErrors);
        PaymentType paymentType = getValidatedPaymentTypeOrNull(data[2], lineErrors);
        validateAmountAndAddError(data[3], lineErrors);
        validateBankAccountNumberAndAddError(paymentType, data[4], lineErrors);
        validateCardNumberAndAddError(paymentType, data[5], lineErrors);
        validateDateAndAddError(data[6], lineErrors);

        return lineErrors;
    }

    private PaymentType getValidatedPaymentTypeOrNull(String paymentTypeString, List<String> lineErrors) {
        if (paymentTypeString.isEmpty()) {
            addErrorForEmptyData(FieldName.PAYMENT_TYPE, lineErrors);
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
            addErrorForEmptyData(FieldName.AMOUNT, lineErrors);
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
            validateDataIsNotEmptyAndAddError(bankAccountNumber, FieldName.BANK_ACCOUNT_NUMBER, lineErrors);
        }
    }

    private void validateCardNumberAndAddError(PaymentType paymentType, String cardNumber, List<String> lineErrors) {
        if (paymentType == PaymentType.CARD) {
            if (cardNumber.isEmpty()) {
                addErrorForEmptyData(FieldName.CARD_NUMBER, lineErrors);
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
            addErrorForEmptyData(FieldName.DATE, lineErrors);
        } else {
            try {
                LocalDate.parse(dateString, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                lineErrors.add(Message.INVALID_DATE_FORMAT.getMessage());
            }
        }
    }

}
