package hu.otpmobil.model;

public class PurchaseByWebShopCustomer {

    private WebShopCustomer webShopCustomer;

    private Integer totalPurchaseAmount;

    public PurchaseByWebShopCustomer(WebShopCustomer webShopCustomer, Integer totalPurchaseAmount) {
        this.webShopCustomer = webShopCustomer;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public WebShopCustomer getWebShopCustomer() {
        return webShopCustomer;
    }

    public Integer getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    @Override
    public String toString() {
        return "PurchaseByWebShopCustomer{" +
                "webShopCustomer=" + webShopCustomer +
                ", totalPurchaseAmount=" + totalPurchaseAmount +
                '}';
    }

}
