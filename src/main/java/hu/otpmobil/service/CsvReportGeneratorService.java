package hu.otpmobil.service;

import hu.otpmobil.model.PurchaseByCustomerDetails;
import hu.otpmobil.model.PurchaseByWebShopCustomer;
import hu.otpmobil.model.WebShopSales;

import java.util.List;

public interface CsvReportGeneratorService {

    void generatePurchaseByCustomerDetailsReport(List<PurchaseByCustomerDetails> purchaseByCustomerDetailsList);

    void generateTopCustomerReport(List<PurchaseByCustomerDetails> topCustomerList);

    void generateWebShopSalesReport(List<WebShopSales> webShopSalesList);

    void generatePurchaseByWebShopCustomerReport(List<PurchaseByWebShopCustomer> purchaseByWebShopCustomerList);

}
