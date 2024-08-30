package hu.otpmobil.service;

import hu.otpmobil.data.DataStore;
import hu.otpmobil.model.Customer;
import hu.otpmobil.model.CustomerDetails;
import hu.otpmobil.model.LineError;
import hu.otpmobil.model.UniqueId;
import hu.otpmobil.util.AppLogger;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;
import hu.otpmobil.validator.CustomerDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static hu.otpmobil.config.ApplicationConstants.*;

public class CustomerDataProcessorServiceImpl implements CustomerDataProcessorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDataProcessorServiceImpl.class);
    private final DataStore dataStore;
    private final CustomerDataValidator validator;

    public CustomerDataProcessorServiceImpl(CustomerDataValidator validator) {
        this.validator = validator;
        this.dataStore = DataStore.getInstance();
    }

    @Override
    public void readAndProcessCustomerData(String filePath, Separator separator) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                String fileName = extractFileName(filePath);
                if (validator.isDataInvalid(fileName, lineNumber, line, separator,
                        REQUIRED_AMOUNT_OF_CUSTOMER_DATA_PER_LINE)) {
                    continue;
                }

                Customer customer = createCustomer(line, separator);
                if (dataStore.isCustomerExistsByUniqueId(customer.getUniqueId())) {
                    AppLogger.logLineError(LOGGER, new LineError()
                            .fileName(fileName)
                            .lineNumber(lineNumber)
                            .lineContent(line)
                            .errors(Collections.singletonList(Message.CUSTOMER_EXISTS.getMessage())));
                    continue;
                }

                saveCustomer(customer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractFileName(String filePath) {
        int lastIndex = filePath.lastIndexOf(BACKSLASH);
        return filePath.substring(lastIndex + 1);
    }

    private Customer createCustomer(String line, Separator separator) {
        String[] data = line.split(separator.getSeparatorRegex());
        return new Customer()
                .uniqueId(new UniqueId()
                        .webShopId(data[0])
                        .customerId(data[1]))
                .details(new CustomerDetails()
                    .name(data[2])
                    .address(data[3]));
    }

    private void saveCustomer(Customer customer) {
        dataStore.saveCustomer(customer);
    }

}
