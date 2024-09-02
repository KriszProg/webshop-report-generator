package hu.otpmobil.model;

import java.util.Objects;

public class WebShopCustomer {

    private UniqueId uniqueId;
    private Customer customer;

    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public WebShopCustomer uniqueId(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public WebShopCustomer customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        WebShopCustomer webShopCustomer = (WebShopCustomer) object;
        return Objects.equals(uniqueId, webShopCustomer.uniqueId)
                && Objects.equals(customer, webShopCustomer.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, customer);
    }

    @Override
    public String toString() {
        return "WebShopCustomer{" +
                "uniqueId=" + uniqueId +
                ", customer=" + customer +
                '}';
    }

}
