package hu.otpmobil.controller;

import hu.otpmobil.model.PurchaseByCustomer;
import hu.otpmobil.model.PurchaseByWebShopCustomer;
import hu.otpmobil.model.WebShopSales;
import hu.otpmobil.service.*;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.List;

import static hu.otpmobil.config.ApplicationConstants.*;

public class ProcessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessController.class);

    private final InputService inputService;
    private final DirectoryCreatorService directoryCreatorService;
    private final CustomerDataProcessorService customerDataProcessorService;
    private final PaymentDataProcessorService paymentDataProcessorService;
    private final ReportDataProviderService reportDataProviderService;
    private final CsvReportGeneratorService csvReportGeneratorService;

    public ProcessController(InputService inputService,
                             DirectoryCreatorService directoryCreatorService,
                             CustomerDataProcessorService customerDataProcessorService,
                             PaymentDataProcessorService paymentDataProcessorService,
                             ReportDataProviderService reportDataProviderService,
                             CsvReportGeneratorService csvReportGeneratorService) {
        this.inputService = inputService;
        this.directoryCreatorService = directoryCreatorService;
        this.customerDataProcessorService = customerDataProcessorService;
        this.paymentDataProcessorService = paymentDataProcessorService;
        this.reportDataProviderService = reportDataProviderService;
        this.csvReportGeneratorService = csvReportGeneratorService;
    }

    private String filePathForCustomerData;
    private String filePathForPaymentData;
    private final Separator separator = Separator.SEMICOLON;

    public void executeProcess() {
        System.out.println(Message.LINE_SEPARATOR.getMessage());
        System.out.println(Message.PROVIDE_SETUP.getMessage());
        System.out.println(Message.LINE_SEPARATOR.getMessage());
        initFilePathForCustomerData();
        initFilePathForPaymentData();
        System.out.println(Message.LINE_SEPARATOR.getMessage());
        System.out.println(Message.SETUPS_COMPLETED.getMessage());
        System.out.println(Message.LINE_SEPARATOR.getMessage());

        LOGGER.info(Message.START_EXECUTION.getMessage());
        LOGGER.info(Message.CREATING_OUTPUT_DIRECTORIES.getMessage());
        directoryCreatorService.createOutputDirectories();

        LOGGER.info(MessageFormat.format(Message.LOADING_CUSTOMER_DATA_FROM_FILE_PATH.getMessage(), filePathForCustomerData));
        customerDataProcessorService.readAndProcessCustomerData(filePathForCustomerData, separator);

        LOGGER.info(MessageFormat.format(Message.LOADING_PAYMENT_DATA_FROM_FILE_PATH.getMessage(), filePathForPaymentData));
        paymentDataProcessorService.readAndProcessPaymentData(filePathForPaymentData, separator);

        LOGGER.info(MessageFormat.format(Message.COLLECTING_DATA_FOR_REPORT.getMessage(), PURCHASE_BY_CUSTOMER_REPORT_NAME));
        List<PurchaseByCustomer> purchaseByCustomerList = reportDataProviderService.getDataForPurchaseByCustomerReport();
        LOGGER.info(MessageFormat.format(Message.GENERATING_REPORT.getMessage(), PURCHASE_BY_CUSTOMER_REPORT_NAME));
        csvReportGeneratorService.generatePurchaseByCustomerReport(purchaseByCustomerList);

        LOGGER.info(MessageFormat.format(Message.COLLECTING_DATA_FOR_REPORT.getMessage(), TOP_CUSTOMER_REPORT_NAME));
        List<PurchaseByCustomer> topCustomerList = reportDataProviderService.getDataForTopCustomerReport();
        LOGGER.info(MessageFormat.format(Message.GENERATING_REPORT.getMessage(), TOP_CUSTOMER_REPORT_NAME));
        csvReportGeneratorService.generateTopCustomerReport(topCustomerList);

        LOGGER.info(MessageFormat.format(Message.COLLECTING_DATA_FOR_REPORT.getMessage(), WEB_SHOP_SALES_REPORT_NAME));
        List<WebShopSales> webShopSalesList = reportDataProviderService.getDataForWebShopSalesReport();
        LOGGER.info(MessageFormat.format(Message.GENERATING_REPORT.getMessage(), WEB_SHOP_SALES_REPORT_NAME));
        csvReportGeneratorService.generateWebShopSalesReport(webShopSalesList);

        LOGGER.info(MessageFormat.format(Message.COLLECTING_DATA_FOR_REPORT.getMessage(), PURCHASE_BY_WEB_SHOP_CUSTOMER_REPORT_NAME));
        List<PurchaseByWebShopCustomer> purchaseByWebShopCustomerList = reportDataProviderService.getDataForPurchaseByWebShopCustomerReport();
        LOGGER.info(MessageFormat.format(Message.GENERATING_REPORT.getMessage(), PURCHASE_BY_WEB_SHOP_CUSTOMER_REPORT_NAME));
        csvReportGeneratorService.generatePurchaseByWebShopCustomerReport(purchaseByWebShopCustomerList);
    }

    private void initFilePathForCustomerData() {
        this.filePathForCustomerData = initFilePath(Message.ENTER_FILE_PATH_FOR_CUSTOMER_DATA);
    }

    private void initFilePathForPaymentData() {
        this.filePathForPaymentData = initFilePath(Message.ENTER_FILE_PATH_FOR_PAYMENT_DATA);
    }

    private String initFilePath(Message message) {
        return inputService.getValidFilePathFromUser(message);
    }

}
