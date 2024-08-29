package hu.otpmobil.validator;

import hu.otpmobil.model.FieldName;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static hu.otpmobil.config.ApplicationConstants.*;

public class CustomerDataValidator extends AbstractValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDataValidator.class);

    public CustomerDataValidator() {
        super(LOGGER);
    }

    @Override
    protected List<String> validateLineDetailsAndCollectLineErrors(String line, Separator separator) {
        List<String> lineErrors = new ArrayList<>();
        String[] data = line.split(separator.getSeparatorRegex());

        validateWebShopIdAndAddError(data[0], lineErrors);
        validateCustomerIdAndAddError(data[1], lineErrors);
        validateDataIsNotEmptyAndAddError(data[2], FieldName.CUSTOMER_NAME, lineErrors);
        validateDataIsNotEmptyAndAddError(data[3], FieldName.CUSTOMER_ADDRESS, lineErrors);

        return lineErrors;
    }

    private void validateWebShopIdAndAddError(String webShopId, List<String> lineErrors) {
        if (webShopId.isEmpty()) {
            addErrorForEmptyData(FieldName.WEB_SHOP_ID, lineErrors);
        } else if (!webShopId.matches(REGEX_WEB_SHOP_ID)) {
            lineErrors.add(Message.INVALID_WEB_SHOP_ID_FORMAT.getMessage());
        }
    }

    private void validateCustomerIdAndAddError(String customerId, List<String> lineErrors) {
        if (customerId.isEmpty()) {
            addErrorForEmptyData(FieldName.CUSTOMER_ID, lineErrors);
        } else if (!customerId.matches(REGEX_CUSTOMER_ID)) {
            lineErrors.add(Message.INVALID_CUSTOMER_ID_FORMAT.getMessage());
        }
    }

}
