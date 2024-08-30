package hu.otpmobil.model;

import java.util.Objects;

public class CustomerDetails {

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public CustomerDetails name(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CustomerDetails address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CustomerDetails that = (CustomerDetails) object;
        return Objects.equals(name, that.name)
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
