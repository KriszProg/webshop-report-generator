package hu.otpmobil.model;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {

    private UniqueId uniqueId;
    private String paymentType;
    private Integer amount;
    private String bankAccountNumber;
    private String cardNumber;
    private LocalDate date;


    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public Payment uniqueId(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Payment paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public Payment amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public Payment bankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Payment cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Payment date(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Payment payment = (Payment) object;
        return Objects.equals(uniqueId, payment.uniqueId)
                && Objects.equals(paymentType, payment.paymentType)
                && Objects.equals(amount, payment.amount)
                && Objects.equals(bankAccountNumber, payment.bankAccountNumber)
                && Objects.equals(cardNumber, payment.cardNumber)
                && Objects.equals(date, payment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, paymentType, amount, bankAccountNumber, cardNumber, date);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "uniqueId=" + uniqueId +
                ", paymentType='" + paymentType + '\'' +
                ", amount=" + amount +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", date=" + date +
                '}';
    }

}
