package hu.otpmobil.model;

public class WebShopSales {

    private String webShopId;
    private Integer cardPaymentTotal;
    private Integer transferPaymentTotal;

    public WebShopSales(String webShopId, Integer cardPaymentTotal, Integer transferPaymentTotal) {
        this.webShopId = webShopId;
        this.cardPaymentTotal = cardPaymentTotal;
        this.transferPaymentTotal = transferPaymentTotal;
    }

    public String getWebShopId() {
        return webShopId;
    }

    public Integer getCardPaymentTotal() {
        return cardPaymentTotal;
    }

    public Integer getTransferPaymentTotal() {
        return transferPaymentTotal;
    }

    @Override
    public String toString() {
        return "WebShopSales{" +
                "webShopId='" + webShopId + '\'' +
                ", cardPaymentTotal=" + cardPaymentTotal +
                ", transferPaymentTotal=" + transferPaymentTotal +
                '}';
    }

}
