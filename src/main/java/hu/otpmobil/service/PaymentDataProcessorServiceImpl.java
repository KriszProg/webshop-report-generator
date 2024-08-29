package hu.otpmobil.service;

import hu.otpmobil.data.DataStore;
import hu.otpmobil.model.LineError;
import hu.otpmobil.model.Payment;
import hu.otpmobil.model.UniqueId;
import hu.otpmobil.util.AppLogger;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;
import hu.otpmobil.validator.PaymentDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static hu.otpmobil.config.ApplicationConstants.*;

public class PaymentDataProcessorServiceImpl implements PaymentDataProcessorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDataProcessorServiceImpl.class);
    private final DataStore dataStore;
    private final PaymentDataValidator validator;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public PaymentDataProcessorServiceImpl(PaymentDataValidator validator) {
        this.validator = validator;
        this.dataStore = DataStore.getInstance();
    }

    @Override
    public void readAndProcessPaymentData(String filePath, Separator separator) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                String fileName = extractFileName(filePath);
                if (validator.isDataInvalid(fileName, lineNumber, line, separator,
                        REQUIRED_AMOUNT_OF_PAYMENT_DATA_PER_LINE)) {
                    continue;
                }

                Payment payment = createPayment(line, separator);
                if (!dataStore.isCustomerExistsByUniqueId(payment.getUniqueId())) {
                    AppLogger.logLineError(LOGGER, new LineError()
                            .fileName(fileName)
                            .lineNumber(lineNumber)
                            .lineContent(line)
                            .errors(Collections.singletonList(Message.CUSTOMER_NOT_EXISTS.getMessage())));
                    continue;
                }

                savePayment(payment);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
