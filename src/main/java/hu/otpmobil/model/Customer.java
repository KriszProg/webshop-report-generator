package hu.otpmobil.model;

import java.util.Objects;

public class Customer {

    private UniqueId uniqueId;
    private String name;
    private String address;

    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public Customer uniqueId(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Customer customer = (Customer) object;
        return Objects.equals(uniqueId, customer.uniqueId)
                && Objects.equals(name, customer.name)
                && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, name, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "uniqueId=" + uniqueId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
