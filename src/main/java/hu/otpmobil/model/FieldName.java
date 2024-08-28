package hu.otpmobil.model;

public enum FieldName {
    AMOUNT("Amount"),
    BANK_ACCOUNT_NUMBER("Bank account number"),
    CARD_NUMBER("Card number"),
    CUSTOMER_ADDRESS("Customer address"),
    CUSTOMER_ID("Customer id"),
    CUSTOMER_NAME("Customer name"),
    DATE("Date"),
    PAYMENT_TYPE("Payment type"),
    WEB_SHOP_ID("Webshop id");

    private final String name;

    FieldName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
