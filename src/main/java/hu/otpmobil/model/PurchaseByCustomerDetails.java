package hu.otpmobil.model;

public class PurchaseByCustomerDetails {

    private CustomerDetails customerDetails;
    private Integer totalPurchaseAmount;

    public PurchaseByCustomerDetails(CustomerDetails customerDetails, Integer totalPurchaseAmount) {
        this.customerDetails = customerDetails;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public Integer getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    @Override
    public String toString() {
        return "PurchaseByCustomerDetails{" +
                "customerDetails=" + customerDetails +
                ", totalPurchaseAmount=" + totalPurchaseAmount +
                '}';
    }

}
