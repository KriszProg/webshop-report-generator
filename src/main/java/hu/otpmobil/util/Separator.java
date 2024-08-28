package hu.otpmobil.util;

public enum Separator {
    SEMICOLON(";", "\\;");
    private final String separatorString;
    private final String separatorRegex;

    Separator(String separatorString, String separatorRegex) {
        this.separatorString = separatorString;
        this.separatorRegex = separatorRegex;
    }

    public String getSeparatorString() {
        return separatorString;
    }

    public String getSeparatorRegex() {
        return separatorRegex;
    }

}
