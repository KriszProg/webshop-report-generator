package hu.otpmobil.service;

import hu.otpmobil.util.Separator;

public interface CustomerDataProcessorService {

    void readAndProcessCustomerData(String filePath, Separator separator);

}
