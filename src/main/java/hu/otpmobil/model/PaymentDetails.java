package hu.otpmobil.model;

import java.time.LocalDate;
import java.util.Objects;

public class PaymentDetails {

    private String paymentType;
    private Integer amount;
    private String bankAccountNumber;
    private String cardNumber;
    private LocalDate date;

    public String getPaymentType() {
        return paymentType;
    }

    public PaymentDetails paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public PaymentDetails amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public PaymentDetails bankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        return this;
    }

    public PaymentDetails cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public PaymentDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PaymentDetails that = (PaymentDetails) object;
        return Objects.equals(paymentType, that.paymentType)
                && Objects.equals(amount, that.amount)
                && Objects.equals(bankAccountNumber, that.bankAccountNumber)
                && Objects.equals(cardNumber, that.cardNumber)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentType, amount, bankAccountNumber, cardNumber, date);
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "paymentType='" + paymentType + '\'' +
                ", amount=" + amount +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", date=" + date +
                '}';
    }

}
