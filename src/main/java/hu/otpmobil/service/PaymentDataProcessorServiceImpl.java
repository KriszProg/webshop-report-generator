package hu.otpmobil.service;

import hu.otpmobil.data.DataStore;
import hu.otpmobil.model.Payment;
import hu.otpmobil.model.UniqueId;
import hu.otpmobil.util.Separator;
import hu.otpmobil.validator.PaymentDataValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static hu.otpmobil.config.ApplicationConstants.BACKSLASH;

public class PaymentDataProcessorServiceImpl implements PaymentDataProcessorService {

    private final DataStore dataStore;
    private final PaymentDataValidator validator;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private List<UniqueId> uniqueIdList;

    public PaymentDataProcessorServiceImpl(PaymentDataValidator validator) {
        this.validator = validator;
        this.dataStore = DataStore.getInstance();
    }

    @Override
    public void readAndProcessPaymentData(String filePath, Separator separator) {
        initUniqueIdList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                String fileName = extractFileName(filePath);
                if (!validator.isPaymentDataValid(fileName, lineNumber, line, separator)) {
                    continue;
                }

                Payment payment = createPayment(line, separator);
                if (!validator.isUniqueIdExists(fileName, lineNumber, line, payment.getUniqueId(), uniqueIdList)) {
                    continue;
                }

                savePayment(payment);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initUniqueIdList() {
        this.uniqueIdList = dataStore.getUniqueIdList();
    }

    private String extractFileName(String filePath) {
        int lastIndex = filePath.lastIndexOf(BACKSLASH);
        return filePath.substring(lastIndex + 1);
    }

    private Payment createPayment(String line, Separator separator) {
        String[] data = line.split(separator.getSeparatorRegex());

        return new Payment()
                .uniqueId(new UniqueId()
                        .webShopId(data[0])
                        .customerId(data[1]))
                .paymentType(data[2])
                .amount(Integer.valueOf(data[3]))
                .bankAccountNumber(data[4])
                .cardNumber(data[5])
                .date(LocalDate.parse(data[6], formatter));
    }

    private void savePayment(Payment payment) {
        dataStore.savePayment(payment);
    }

}
