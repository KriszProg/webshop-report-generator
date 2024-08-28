package hu.otpmobil.model;

import java.util.Arrays;

public enum PaymentType {
    CARD("card"),
    TRANSFER("transfer");

    private final String name;

    PaymentType (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PaymentType getPaymentTypeByName(String name) {
        return Arrays.stream(values())
                .filter(paymentType -> name.toLowerCase().equals(paymentType.name))
                .findFirst()
                .orElse(null);
    }

}
