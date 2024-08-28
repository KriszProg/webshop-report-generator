package hu.otpmobil.config;

public final class ApplicationConstants {

    private ApplicationConstants() {}

    // General constants
    public static final String BACKSLASH ="\\";
    public static final Integer NUMBER_OF_TOP_LIST_ELEMENTS = 2;
    public static final String REGEX_CUSTOMER_ID = "^A\\d{2}$";
    public static final String REGEX_WEB_SHOP_ID = "^WS\\d{2}$";
    public static final int REQUIRED_AMOUNT_OF_CUSTOMER_DATA_PER_LINE = 4;
    public static final int REQUIRED_AMOUNT_OF_PAYMENT_DATA_PER_LINE = 7;

    // Log and Reports related constants
    public static final String LOG_BASE_PATH = "C:/webshop/log/";
    public static final String REPORT_BASE_PATH = "C:/webshop/reports/";
    public static final String[] CUSTOMER_PAYMENT_REPORT_HEADER = {"NAME", "ADDRESS", "vásárlás összesen"};
    public static final String CUSTOMER_PAYMENT_REPORT_NAME = "report01.csv";
    public static final String CUSTOMER_PAYMENT_REPORT_FULL_PATH = REPORT_BASE_PATH + CUSTOMER_PAYMENT_REPORT_NAME;
    public static final String[] TOP_CUSTOMER_REPORT_HEADER = CUSTOMER_PAYMENT_REPORT_HEADER;
    public static final String TOP_CUSTOMER_REPORT_NAME = "top.csv";
    public static final String TOP_CUSTOMER_REPORT_FULL_PATH = REPORT_BASE_PATH + TOP_CUSTOMER_REPORT_NAME;
    public static final String[] WEB_SHOP_SALES_REPORT_HEADER = {"WEBSHOP", "kártyás vásárlások összege", "átutalásos vásárlások összege"};
    public static final String WEB_SHOP_SALES_REPORT_NAME = "report02.csv";
    public static final String WEB_SHOP_SALES_REPORT_FULL_PATH = REPORT_BASE_PATH + WEB_SHOP_SALES_REPORT_NAME;

}
