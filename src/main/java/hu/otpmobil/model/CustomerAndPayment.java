package hu.otpmobil.model;

import java.time.LocalDate;
import java.util.Objects;

public class CustomerAndPayment {

    private UniqueId uniqueId;
    private Customer customer;
    private String paymentType;
    private Integer paymentAmount;
    private String bankAccountNumber;
    private String cardNumber;
    private LocalDate paymentDate;

    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public CustomerAndPayment uniqueId(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerAndPayment customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public CustomerAndPayment paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public CustomerAndPayment paymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public CustomerAndPayment bankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CustomerAndPayment cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public CustomerAndPayment paymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CustomerAndPayment that = (CustomerAndPayment) object;
        return Objects.equals(uniqueId, that.uniqueId)
                && Objects.equals(customer, that.customer)
                && Objects.equals(paymentType, that.paymentType)
                && Objects.equals(paymentAmount, that.paymentAmount)
                && Objects.equals(bankAccountNumber, that.bankAccountNumber)
                && Objects.equals(cardNumber, that.cardNumber)
                && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, customer, paymentType, paymentAmount, bankAccountNumber, cardNumber, paymentDate);
    }

    @Override
    public String toString() {
        return "CustomerAndPayment{" +
                "uniqueId=" + uniqueId +
                ", customer=" + customer +
                ", paymentType='" + paymentType + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }

}
