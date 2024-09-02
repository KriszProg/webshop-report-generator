package hu.otpmobil.config;

import java.time.format.DateTimeFormatter;

public final class ApplicationConstants {

    private ApplicationConstants() {}

    // General constants
    public static final String BACKSLASH ="\\";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    public static final Integer NUMBER_OF_TOP_LIST_ELEMENTS = 2;
    public static final String REGEX_CUSTOMER_ID = "^A\\d{2}$";
    public static final String REGEX_WEB_SHOP_ID = "^WS\\d{2}$";
    public static final int REQUIRED_AMOUNT_OF_CUSTOMER_DATA_PER_LINE = 4;
    public static final int REQUIRED_AMOUNT_OF_PAYMENT_DATA_PER_LINE = 7;

    // Log and Reports related constants
    public static final String LOG_BASE_PATH = "C:/webshop/log/";
    public static final String REPORT_BASE_PATH = "C:/webshop/reports/";
    public static final String[] PURCHASE_BY_CUSTOMER_REPORT_HEADER = {"NAME", "ADDRESS", "vásárlás összesen"};
    public static final String PURCHASE_BY_CUSTOMER_REPORT_NAME = "report01.csv";
    public static final String PURCHASE_BY_CUSTOMER_REPORT_FULL_PATH = REPORT_BASE_PATH + PURCHASE_BY_CUSTOMER_REPORT_NAME;
    public static final String[] PURCHASE_BY_CUSTOMER_PER_YEAR_REPORT_HEADER = {"YEAR", "NAME", "ADDRESS", "vásárlás összesen"};
    public static final String PURCHASE_BY_CUSTOMER_REPORT_PER_YEAR_NAME = "report01_per_year.csv";
    public static final String PURCHASE_BY_CUSTOMER_REPORT_PER_YEAR_FULL_PATH = REPORT_BASE_PATH + PURCHASE_BY_CUSTOMER_REPORT_PER_YEAR_NAME;
    public static final String[] PURCHASE_BY_WEB_SHOP_CUSTOMER_REPORT_HEADER = {"WEBSHOP_ID", "CUSOMER_ID", "NAME", "ADDRESS", "vásárlás összesen"};
    public static final String PURCHASE_BY_WEB_SHOP_CUSTOMER_REPORT_NAME = "report03.csv";
    public static final String PURCHASE_BY_WEB_SHOP_CUSTOMER_REPORT_FULL_PATH = REPORT_BASE_PATH + PURCHASE_BY_WEB_SHOP_CUSTOMER_REPORT_NAME;
    public static final String[] PURCHASE_BY_WEB_SHOP_CUSTOMER_PER_YEAR_REPORT_HEADER = {"YEAR", "WEBSHOP_ID", "CUSOMER_ID", "NAME", "ADDRESS", "vásárlás összesen"};
    public static final String PURCHASE_BY_WEB_SHOP_CUSTOMER_PER_YEAR_REPORT_NAME = "report03_per_year.csv";
    public static final String PURCHASE_BY_WEB_SHOP_CUSTOMER_PER_YEAR_REPORT_FULL_PATH = REPORT_BASE_PATH + PURCHASE_BY_WEB_SHOP_CUSTOMER_PER_YEAR_REPORT_NAME;
    public static final String[] TOP_CUSTOMER_REPORT_HEADER = PURCHASE_BY_CUSTOMER_REPORT_HEADER;
    public static final String TOP_CUSTOMER_REPORT_NAME = "top.csv";
    public static final String TOP_CUSTOMER_REPORT_FULL_PATH = REPORT_BASE_PATH + TOP_CUSTOMER_REPORT_NAME;
    public static final String[] WEB_SHOP_SALES_REPORT_HEADER = {"WEBSHOP", "kártyás vásárlások összege", "átutalásos vásárlások összege"};
    public static final String WEB_SHOP_SALES_REPORT_NAME = "report02.csv";
    public static final String WEB_SHOP_SALES_REPORT_FULL_PATH = REPORT_BASE_PATH + WEB_SHOP_SALES_REPORT_NAME;

}
