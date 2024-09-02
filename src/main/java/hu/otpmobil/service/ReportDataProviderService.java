package hu.otpmobil.service;

import hu.otpmobil.model.PurchaseByCustomer;
import hu.otpmobil.model.PurchaseByWebShopCustomer;
import hu.otpmobil.model.WebShopSales;

import java.util.List;

public interface ReportDataProviderService {

    List<PurchaseByCustomer> getDataForPurchaseByCustomerReport();

    List<PurchaseByCustomer> getDataForTopCustomerReport();

    List<WebShopSales> getDataForWebShopSalesReport();

    List<PurchaseByWebShopCustomer> getDataForPurchaseByWebShopCustomerReport();

}
