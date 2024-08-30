package hu.otpmobil.model;

import java.util.Objects;

public class Customer {

    private UniqueId uniqueId;
    private CustomerDetails details;

    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public Customer uniqueId(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public CustomerDetails getDetails() {
        return details;
    }

    public Customer details(CustomerDetails details) {
        this.details = details;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Customer customer = (Customer) object;
        return Objects.equals(uniqueId, customer.uniqueId)
                && Objects.equals(details, customer.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, details);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "uniqueId=" + uniqueId +
                ", details=" + details +
                '}';
    }

}
