package hu.otpmobil.service;

import hu.otpmobil.data.DataStore;
import hu.otpmobil.model.Customer;
import hu.otpmobil.model.UniqueId;
import hu.otpmobil.util.Separator;
import hu.otpmobil.validator.CustomerDataValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static hu.otpmobil.config.ApplicationConstants.BACKSLASH;

public class CustomerDataProcessorServiceImpl implements CustomerDataProcessorService {

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
                if (!validator.isCustomerDataValid(fileName, lineNumber, line, separator)) {
                    continue;
                }

                Customer customer = createCustomer(line, separator);
                if (validator.isUniqueIdExists(fileName, lineNumber, line, customer.getUniqueId(), dataStore.getUniqueIdList())) {
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
                .name(data[2])
                .address(data[3]);
    }

    private void saveCustomer(Customer customer) {
        dataStore.saveCustomer(customer);
    }

}
