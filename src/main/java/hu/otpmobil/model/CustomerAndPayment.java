package hu.otpmobil.model;

import java.util.Objects;

public class CustomerAndPayment {

    private UniqueId uniqueId;
    private CustomerDetails customer;
    private PaymentDetails payment;

    public CustomerAndPayment(UniqueId uniqueId, CustomerDetails customer, PaymentDetails payment) {
        this.uniqueId = uniqueId;
        this.customer = customer;
        this.payment = payment;
    }

    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public CustomerDetails getCustomer() {
        return customer;
    }

    public PaymentDetails getPayment() {
        return payment;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CustomerAndPayment that = (CustomerAndPayment) object;
        return Objects.equals(uniqueId, that.uniqueId)
                && Objects.equals(customer, that.customer)
                && Objects.equals(payment, that.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, customer, payment);
    }

    @Override
    public String toString() {
        return "CustomerAndPayment{" +
                "uniqueId=" + uniqueId +
                ", customer=" + customer +
                ", payment=" + payment +
                '}';
    }

}
