package hu.otpmobil.model;

public class PurchaseByWebShopCustomerPerYear {

    private Integer year;
    private WebShopCustomer webShopCustomer;
    private Integer totalPurchaseAmount;

    public PurchaseByWebShopCustomerPerYear(Integer year, WebShopCustomer webShopCustomer, Integer totalPurchaseAmount) {
        this.year = year;
        this.webShopCustomer = webShopCustomer;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public WebShopCustomer getWebShopCustomer() {
        return webShopCustomer;
    }

    public Integer getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "PurchaseByWebShopCustomerPerYear{" +
                "year=" + year +
                ", webShopCustomer=" + webShopCustomer +
                ", totalPurchaseAmount=" + totalPurchaseAmount +
                '}';
    }

}
