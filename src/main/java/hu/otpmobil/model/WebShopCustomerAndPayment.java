package hu.otpmobil.model;

import java.time.LocalDate;
import java.util.Objects;

public class WebShopCustomerAndPayment {

    private WebShopCustomer webShopCustomer;
    private String paymentType;
    private Integer paymentAmount;
    private String bankAccountNumber;
    private String cardNumber;
    private LocalDate paymentDate;

    public WebShopCustomer getWebShopCustomer() {
        return webShopCustomer;
    }

    public WebShopCustomerAndPayment webShopCustomer(WebShopCustomer webShopCustomer) {
        this.webShopCustomer = webShopCustomer;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public WebShopCustomerAndPayment paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public WebShopCustomerAndPayment paymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public WebShopCustomerAndPayment bankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public WebShopCustomerAndPayment cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public WebShopCustomerAndPayment paymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        WebShopCustomerAndPayment that = (WebShopCustomerAndPayment) object;
        return Objects.equals(webShopCustomer, that.webShopCustomer)
                && Objects.equals(paymentType, that.paymentType)
                && Objects.equals(paymentAmount, that.paymentAmount)
                && Objects.equals(bankAccountNumber, that.bankAccountNumber)
                && Objects.equals(cardNumber, that.cardNumber)
                && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(webShopCustomer, paymentType, paymentAmount, bankAccountNumber, cardNumber, paymentDate);
    }

    @Override
    public String toString() {
        return "WebShopCustomerAndPayment{" +
                "webShopCustomer=" + webShopCustomer +
                ", paymentType='" + paymentType + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }

}
