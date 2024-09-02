package hu.otpmobil.data;

import hu.otpmobil.model.WebShopCustomer;
import hu.otpmobil.model.WebShopCustomerAndPayment;
import hu.otpmobil.model.Payment;
import hu.otpmobil.model.UniqueId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataStore {

    private static DataStore instance;

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    private final List<WebShopCustomer> webShopCustomers = new ArrayList<>();

    private final List<Payment> payments = new ArrayList<>();

    private DataStore() {}

    public void saveWebShopCustomer(WebShopCustomer webShopCustomer) {
        webShopCustomers.add(webShopCustomer);
    }

    public boolean isWebShopCustomerExistsByUniqueId(UniqueId uniqueId) {
        return webShopCustomers.stream()
                .anyMatch(customer -> uniqueId.equals(customer.getUniqueId()));
    }

    public void savePayment(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<WebShopCustomerAndPayment> getWebShopCustomerAndPaymentList() {
        Map<UniqueId, WebShopCustomer> webShopCustomerMap = webShopCustomers.stream()
                .collect(Collectors.toMap(WebShopCustomer::getUniqueId, webShopCustomer -> webShopCustomer));

        return payments.stream()
                .map(payment -> {
                    WebShopCustomer webShopCustomer = webShopCustomerMap.get(payment.getUniqueId());
                    return new WebShopCustomerAndPayment()
                            .webShopCustomer(webShopCustomer)
                            .paymentType(payment.getPaymentType())
                            .paymentAmount(payment.getAmount())
                            .bankAccountNumber(payment.getBankAccountNumber())
                            .cardNumber(payment.getCardNumber())
                            .paymentDate(payment.getDate());
                })
                .collect(Collectors.toList());
    }

}
