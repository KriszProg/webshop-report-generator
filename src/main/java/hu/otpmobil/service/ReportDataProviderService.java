package hu.otpmobil.service;

import hu.otpmobil.model.PurchaseByCustomerDetails;
import hu.otpmobil.model.PurchaseByWebShopCustomer;
import hu.otpmobil.model.WebShopSales;

import java.util.List;

public interface ReportDataProviderService {

    List<PurchaseByCustomerDetails> getDataForPurchaseByCustomerDetailsReport();

    List<PurchaseByCustomerDetails> getDataForTopCustomerReport();

    List<WebShopSales> getDataForWebShopSalesReport();

    List<PurchaseByWebShopCustomer> getDataForPurchaseByWebShopCustomerReport();

}
