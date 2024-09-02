package hu.otpmobil.service;

import hu.otpmobil.model.*;

import java.util.List;

public interface CsvReportGeneratorService {

    void generatePurchaseByCustomerReport(List<PurchaseByCustomer> purchaseByCustomerList);

    void generatePurchaseByCustomerPerYearReport(List<PurchaseByCustomerPerYear> purchaseByCustomerPerYearList);

    void generateTopCustomerReport(List<PurchaseByCustomer> topCustomerList);

    void generateWebShopSalesReport(List<WebShopSales> webShopSalesList);

    void generatePurchaseByWebShopCustomerReport(List<PurchaseByWebShopCustomer> purchaseByWebShopCustomerList);

    void generatePurchaseByWebShopCustomerPerYearReport(List<PurchaseByWebShopCustomerPerYear> purchaseByWebShopCustomerPerYearList);

}
