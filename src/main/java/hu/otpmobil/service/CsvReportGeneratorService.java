package hu.otpmobil.service;

import hu.otpmobil.model.CustomerPayment;
import hu.otpmobil.model.WebShopSales;

import java.util.List;

public interface CsvReportGeneratorService {

    void generateCustomerPaymentReport(List<CustomerPayment> customerPayments);

    void generateTopCustomerReport(List<CustomerPayment> topCustomerPayments);

    void generateWebShopSalesReport(List<WebShopSales> webShopSalesList);

}
