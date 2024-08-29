package hu.otpmobil.util;

public enum Message {
    CREATING_OUTPUT_DIRECTORIES("Creating output directories..."),
    COLLECTING_DATA_FOR_REPORT("Collecting data for {0}..."),
    CUSTOMER_EXISTS("Customer already exists based on the provided ids!"),
    CUSTOMER_NOT_EXISTS("Customer not exists based on the provided ids!"),
    DATA_CANNOT_BE_EMPTY("{0} cannot be empty!"),
    DATA_PROCESSING_ERROR("An error occurred during process data from file!  DETAILS: {file : '{}', lineNumber : '{}', lineContent : '{}', errors : '{}'}"),
    ENTER_FILE_PATH_FOR_CUSTOMER_DATA("[STEP_1] Enter the full filepath for CUSTOMER data (example: C:\\webshop-source\\customer.csv) and hit Enter:"),
    ENTER_FILE_PATH_FOR_PAYMENT_DATA("[STEP_2] Enter the full filepath for PAYMENT data (example: C:\\webshop-source\\payments.csv) and hit Enter:"),
    EXPECTED_SEPARATOR_NOT_PRESENT("Line does not contain the expected separator!"),
    GENERATING_REPORT("Generating {0}..."),
    INVALID_AMOUNT("Invalid amount (amount should be greater then 0)!"),
    INVALID_AMOUNT_FORMAT("Invalid amount format!"),
    INVALID_CARD_NUMBER("Invalid card number!"),
    INVALID_CUSTOMER_ID_FORMAT("Invalid customer id format!"),
    INVALID_DATE_FORMAT("Invalid date format!"),
    INVALID_FILE_PATH("Invalid file path! Please try again."),
    INVALID_LINE_CONTENT("Invalid line content (expected exactly {0} data per line)!"),
    INVALID_PAYMENT_TYPE("Invalid payment type!"),
    INVALID_WEB_SHOP_ID_FORMAT("Invalid webshop id format!"),
    LINE_SEPARATOR("=".repeat(45)),
    LOADING_CUSTOMER_DATA_FROM_FILE_PATH("Loading CUSTOMER data from {0}..."),
    LOADING_PAYMENT_DATA_FROM_FILE_PATH("Loading PAYMENT data from {0}..."),
    PROVIDE_SETUP("[SETUP] Please provide setups in 2 steps..."),
    SETUPS_COMPLETED("Setups completed successfully!"),
    START_EXECUTION("Start execution...");

    private final String message;

    Message (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
