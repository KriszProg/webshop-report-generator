package hu.otpmobil.validator;

import hu.otpmobil.model.FieldName;
import hu.otpmobil.model.LineError;
import hu.otpmobil.model.UniqueId;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static hu.otpmobil.config.ApplicationConstants.*;

public class CustomerDataValidator extends AbstractValidator {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomerDataValidator.class);

    public CustomerDataValidator() {
        super(LOGGER);
    }

    public boolean isCustomerDataValid(String fileName, Integer lineNumber, String line, Separator separator) {
        if (!isPaymentDataSyntacticallyValid(fileName, lineNumber, line, separator, REQUIRED_AMOUNT_OF_CUSTOMER_DATA_PER_LINE)) {
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
        if (isUniqueIdExists(uniqueId, uniqueIdList)) {
            LineError lineError = new LineError().fileName(fileName).lineNumber(lineNumber).lineContent(line);
            lineError.addError(Message.CUSTOMER_EXISTS.getMessage());
            logLineError(lineError);
            return true;
        }
        return false;
    }

    private List<String> validateLineDetailsAndCollectLineError(String line, Separator separator) {
        List<String> lineErrors = new ArrayList<>();
        String[] data = line.split(separator.getSeparatorRegex());

        validateWebShopIdAndAddError(data[0], lineErrors);
        validateCustomerIdAndAddError(data[1], lineErrors);
        validateCustomerNameAndAddError(data[2], lineErrors);
        validateCustomerAddressAndAddError(data[3], lineErrors);

        return lineErrors;
    }

    private void validateWebShopIdAndAddError(String webShopId, List<String> lineErrors) {
        if (webShopId.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.WEB_SHOP_ID.getName()));
        } else if (!webShopId.matches(REGEX_WEB_SHOP_ID)) {
            lineErrors.add(Message.INVALID_WEB_SHOP_ID_FORMAT.getMessage());
        }
    }

    private void validateCustomerIdAndAddError(String customerId, List<String> lineErrors) {
        if (customerId.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.CUSTOMER_ID.getName()));
        } else if (!customerId.matches(REGEX_CUSTOMER_ID)) {
            lineErrors.add(Message.INVALID_CUSTOMER_ID_FORMAT.getMessage());
        }
    }

    private void validateCustomerNameAndAddError(String customerName, List<String> lineErrors) {
        if (customerName.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.CUSTOMER_NAME.getName()));
        }
    }

    private void validateCustomerAddressAndAddError(String customerAddress, List<String> lineErrors) {
        if (customerAddress.isEmpty()) {
            lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), FieldName.CUSTOMER_ADDRESS.getName()));
        }
    }

}
