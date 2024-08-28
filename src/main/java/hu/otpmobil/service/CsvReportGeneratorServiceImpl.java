package hu.otpmobil.service;

import hu.otpmobil.model.CustomerPayment;
import hu.otpmobil.model.WebShopSales;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static hu.otpmobil.config.ApplicationConstants.*;

public class CsvReportGeneratorServiceImpl implements CsvReportGeneratorService {

    @Override
    public void generateCustomerPaymentReport(List<CustomerPayment> customerPayments) {
        File file = new File(CUSTOMER_PAYMENT_REPORT_FULL_PATH);

        try (CSVPrinter csvPrinter = getCSVPrinter(file.getPath(), CUSTOMER_PAYMENT_REPORT_HEADER)) {
            for (CustomerPayment payment : customerPayments) {
                csvPrinter.printRecord(payment.getName(), payment.getAddress(), payment.getTotalPayment());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateTopCustomerReport(List<CustomerPayment> topCustomerPayments) {
        File file = new File(TOP_CUSTOMER_REPORT_FULL_PATH);

        try (CSVPrinter csvPrinter = getCSVPrinter(file.getPath(), TOP_CUSTOMER_REPORT_HEADER)) {
            for (CustomerPayment payment : topCustomerPayments) {
                csvPrinter.printRecord(payment.getName(), payment.getAddress(), payment.getTotalPayment());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateWebShopSalesReport(List<WebShopSales> webShopSalesList) {
        File file = new File(WEB_SHOP_SALES_REPORT_FULL_PATH);

        try (CSVPrinter csvPrinter = getCSVPrinter(file.getPath(), WEB_SHOP_SALES_REPORT_HEADER)) {
            for (WebShopSales webShopSales : webShopSalesList) {
                csvPrinter.printRecord(webShopSales.getWebShopId(), webShopSales.getCardPaymentTotal(),
                        webShopSales.getTransferPaymentTotal());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CSVPrinter getCSVPrinter (String filPath, String[] headers) throws IOException {
        FileWriter writer = new FileWriter(filPath);
        return new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                .setDelimiter(';')
                .setHeader(headers)
                .build());
    }

}
