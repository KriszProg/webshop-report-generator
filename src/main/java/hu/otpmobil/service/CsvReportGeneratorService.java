package hu.otpmobil.service;

import hu.otpmobil.model.PurchaseByCustomer;
import hu.otpmobil.model.PurchaseByWebShopCustomer;
import hu.otpmobil.model.WebShopSales;

import java.util.List;

public interface CsvReportGeneratorService {

    void generatePurchaseByCustomerReport(List<PurchaseByCustomer> purchaseByCustomerList);

    void generateTopCustomerReport(List<PurchaseByCustomer> topCustomerList);

    void generateWebShopSalesReport(List<WebShopSales> webShopSalesList);

    void generatePurchaseByWebShopCustomerReport(List<PurchaseByWebShopCustomer> purchaseByWebShopCustomerList);

}
