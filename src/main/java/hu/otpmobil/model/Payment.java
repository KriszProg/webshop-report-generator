package hu.otpmobil.model;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {

    private UniqueId uniqueId;
    private PaymentDetails details;


    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public Payment uniqueId(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public PaymentDetails getDetails() {
        return details;
    }

    public Payment details(PaymentDetails details) {
        this.details = details;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Payment payment = (Payment) object;
        return Objects.equals(uniqueId, payment.uniqueId)
                && Objects.equals(details, payment.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, details);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "uniqueId=" + uniqueId +
                ", details=" + details +
                '}';
    }

}
