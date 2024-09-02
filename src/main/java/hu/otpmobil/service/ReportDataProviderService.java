package hu.otpmobil.service;

import hu.otpmobil.model.*;

import java.util.List;

public interface ReportDataProviderService {

    List<PurchaseByCustomer> getDataForPurchaseByCustomerReport();

    List<PurchaseByCustomerPerYear> getDataForPurchaseByCustomerPerYearReport();

    List<PurchaseByCustomer> getDataForTopCustomerReport();

    List<WebShopSales> getDataForWebShopSalesReport();

    List<PurchaseByWebShopCustomer> getDataForPurchaseByWebShopCustomerReport();

    List<PurchaseByWebShopCustomerPerYear> getDataForPurchaseByWebShopCustomerPerYearReport();

}
