package hu.otpmobil.service;

import hu.otpmobil.util.Separator;

public interface PaymentDataProcessorService {

    void readAndProcessPaymentData(String filePath, Separator separator);

}
