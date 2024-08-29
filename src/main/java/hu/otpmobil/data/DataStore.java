package hu.otpmobil.data;

import hu.otpmobil.model.Customer;
import hu.otpmobil.model.Payment;
import hu.otpmobil.model.UniqueId;

import java.util.ArrayList;
import java.util.List;

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

}
