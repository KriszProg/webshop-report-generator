package hu.otpmobil.model;

public class CustomerPayment {

    private String name;
    private String address;
    private Integer totalPayment;

    public CustomerPayment(String name, String address, Integer totalPayment) {
        this.name = name;
        this.address = address;
        this.totalPayment = totalPayment;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getTotalPayment() {
        return totalPayment;
    }

    @Override
    public String toString() {
        return "CustomerPayment{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", totalPayment=" + totalPayment +
                '}';
    }

}
