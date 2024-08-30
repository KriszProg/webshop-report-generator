package hu.otpmobil.model;

public class PurchaseByWebShopCustomer {

    private Customer customer;

    private Integer totalPurchaseAmount;

    public PurchaseByWebShopCustomer(Customer customer, Integer totalPurchaseAmount) {
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
        return "PurchaseByWebShopCustomer{" +
                "customer=" + customer +
                ", totalPurchaseAmount=" + totalPurchaseAmount +
                '}';
    }

}
