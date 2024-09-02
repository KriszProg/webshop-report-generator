package hu.otpmobil.model;

public class PurchaseByCustomerPerYear {

    private Integer year;
    private Customer customer;
    private Integer totalPurchaseAmount;

    public PurchaseByCustomerPerYear(Integer year, Customer customer, Integer totalPurchaseAmount) {
        this.year = year;
        this.customer = customer;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public Integer getYear() {
        return year;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    @Override
    public String toString() {
        return "PurchaseByCustomerPerYear{" +
                "year=" + year +
                ", customer=" + customer +
                ", totalPurchaseAmount=" + totalPurchaseAmount +
                '}';
    }

}
