package hu.otpmobil.service;

import hu.otpmobil.model.CustomerPayment;
import hu.otpmobil.model.WebShopSales;

import java.util.List;

public interface ReportDataProviderService {

    List<CustomerPayment> getDataForCustomerPaymentReport();
    List<CustomerPayment> getDataForTopCustomerReport();

    List<WebShopSales> getDataForWebShopSalesReport();

}
