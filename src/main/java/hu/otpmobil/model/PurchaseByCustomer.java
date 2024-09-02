package hu.otpmobil.model;

public class PurchaseByCustomer {

    private Customer customer;
    private Integer totalPurchaseAmount;

    public PurchaseByCustomer(Customer customer, Integer totalPurchaseAmount) {
        this.customer = customer;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    @Override
    public String toString() {
        return "PurchaseByCustomer{" +
                "customer=" + customer +
                ", totalPurchaseAmount=" + totalPurchaseAmount +
                '}';
    }

}
