package hu.otpmobil;

import hu.otpmobil.controller.ProcessController;
import hu.otpmobil.service.*;
import hu.otpmobil.validator.CustomerDataValidator;
import hu.otpmobil.validator.PaymentDataValidator;

public class WebShopReportGeneratorApp {
    public static void main( String[] args ) {

        InputService inputService = new InputServiceImpl();
        DirectoryCreatorService directoryCreatorService = new DirectoryCreatorServiceImpl();

        CustomerDataValidator customerDataValidator = new CustomerDataValidator();
        CustomerDataProcessorService customerDataProcessorService = new CustomerDataProcessorServiceImpl(customerDataValidator);

        PaymentDataValidator paymentDataValidator = new PaymentDataValidator();
        PaymentDataProcessorService paymentDataProcessorService = new PaymentDataProcessorServiceImpl(paymentDataValidator);

        ReportDataProviderService reportDataProviderService = new ReportDataProviderServiceImpl();
        CsvReportGeneratorService csvReportGeneratorService = new CsvReportGeneratorServiceImpl();

        ProcessController controller = new ProcessController(inputService, directoryCreatorService,
                customerDataProcessorService, paymentDataProcessorService, reportDataProviderService,
                csvReportGeneratorService);

        controller.executeProcess();
    }

}
