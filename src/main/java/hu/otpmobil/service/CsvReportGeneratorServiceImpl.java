package hu.otpmobil.service;

import hu.otpmobil.model.*;
import hu.otpmobil.util.Separator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static hu.otpmobil.config.ApplicationConstants.*;

public class CsvReportGeneratorServiceImpl implements CsvReportGeneratorService {

    @Override
    public void generatePurchaseByCustomerReport(List<PurchaseByCustomer> purchaseByCustomerList) {
        File file = new File(PURCHASE_BY_CUSTOMER_REPORT_FULL_PATH);

        try (CSVPrinter csvPrinter = getCSVPrinter(file.getPath(), PURCHASE_BY_CUSTOMER_REPORT_HEADER)) {
            for (PurchaseByCustomer purchase : purchaseByCustomerList) {
                Customer customer = purchase.getCustomer();
                csvPrinter.printRecord(customer.getName(), customer.getAddress(), purchase.getTotalPurchaseAmount());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generatePurchaseByCustomerPerYearReport(List<PurchaseByCustomerPerYear> purchaseByCustomerPerYearList) {
        File file = new File(PURCHASE_BY_CUSTOMER_REPORT_PER_YEAR_FULL_PATH);

        try (CSVPrinter csvPrinter = getCSVPrinter(file.getPath(), PURCHASE_BY_CUSTOMER_PER_YEAR_REPORT_HEADER)) {
            for (PurchaseByCustomerPerYear purchase : purchaseByCustomerPerYearList) {
                Customer customer = purchase.getCustomer();
                csvPrinter.printRecord(purchase.getYear(), customer.getName(), customer.getAddress(),
                        purchase.getTotalPurchaseAmount());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateTopCustomerReport(List<PurchaseByCustomer> topCustomerList) {
        File file = new File(TOP_CUSTOMER_REPORT_FULL_PATH);

        try (CSVPrinter csvPrinter = getCSVPrinter(file.getPath(), TOP_CUSTOMER_REPORT_HEADER)) {
            for (PurchaseByCustomer purchase : topCustomerList) {
                Customer customer = purchase.getCustomer();
                csvPrinter.printRecord(customer.getName(), customer.getAddress(), purchase.getTotalPurchaseAmount());
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

    @Override
    public void generatePurchaseByWebShopCustomerReport(List<PurchaseByWebShopCustomer> purchaseByWebShopCustomerList) {
        File file = new File(PURCHASE_BY_WEB_SHOP_CUSTOMER_REPORT_FULL_PATH);

        try (CSVPrinter csvPrinter = getCSVPrinter(file.getPath(), PURCHASE_BY_WEB_SHOP_CUSTOMER_REPORT_HEADER)) {
            for (PurchaseByWebShopCustomer purchase : purchaseByWebShopCustomerList) {
                UniqueId uniqueId = purchase.getWebShopCustomer().getUniqueId();
                Customer customer = purchase.getWebShopCustomer().getCustomer();
                csvPrinter.printRecord(uniqueId.getWebShopId(), uniqueId.getCustomerId(), customer.getName(),
                        customer.getAddress(), purchase.getTotalPurchaseAmount());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generatePurchaseByWebShopCustomerPerYearReport(List<PurchaseByWebShopCustomerPerYear> purchaseByWebShopCustomerPerYearList) {
        File file = new File(PURCHASE_BY_WEB_SHOP_CUSTOMER_PER_YEAR_REPORT_FULL_PATH);

        try (CSVPrinter csvPrinter = getCSVPrinter(file.getPath(), PURCHASE_BY_WEB_SHOP_CUSTOMER_PER_YEAR_REPORT_HEADER)) {
            for (PurchaseByWebShopCustomerPerYear purchase : purchaseByWebShopCustomerPerYearList) {
                WebShopCustomer webShopCustomer = purchase.getWebShopCustomer();
                csvPrinter.printRecord(purchase.getYear(), webShopCustomer.getUniqueId().getWebShopId(),
                        webShopCustomer.getUniqueId().getCustomerId(), webShopCustomer.getCustomer().getName(),
                        webShopCustomer.getCustomer().getAddress(), purchase.getTotalPurchaseAmount());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CSVPrinter getCSVPrinter (String filPath, String[] headers) throws IOException {
        FileWriter writer = new FileWriter(filPath);
        return new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                .setDelimiter(Separator.SEMICOLON.getSeparatorString())
                .setHeader(headers)
                .build());
    }

}
