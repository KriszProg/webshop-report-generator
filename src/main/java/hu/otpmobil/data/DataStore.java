package hu.otpmobil.data;

import hu.otpmobil.model.Customer;
import hu.otpmobil.model.CustomerAndPayment;
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

    private final List<Customer> customers = new ArrayList<>();

    private final List<Payment> payments = new ArrayList<>();

    private DataStore() {}

    public void saveCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public boolean isCustomerExistsByUniqueId(UniqueId uniqueId) {
        return customers.stream()
                .anyMatch(customer -> uniqueId.equals(customer.getUniqueId()));
    }

    public void savePayment(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<CustomerAndPayment> getCustomerAndPaymentList() {
        Map<UniqueId, Customer> customerMap = customers.stream()
                .collect(Collectors.toMap(Customer::getUniqueId, customer -> customer));

        return payments.stream()
                .map(payment -> {
                    Customer customer = customerMap.get(payment.getUniqueId());
                    return new CustomerAndPayment(customer.getUniqueId(), customer.getDetails(), payment.getDetails());
                })
                .collect(Collectors.toList());
    }

}
