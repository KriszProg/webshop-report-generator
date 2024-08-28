package hu.otpmobil.model;

import java.util.ArrayList;
import java.util.List;

public class LineError {

    private String fileName;
    private Integer lineNumber;
    private String lineContent;
    private List<String> errors = new ArrayList<>();

    public String getFileName() {
        return fileName;
    }

    public LineError fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public LineError lineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    public String getLineContent() {
        return lineContent;
    }

    public LineError lineContent(String lineContent) {
        this.lineContent = lineContent;
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public LineError errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public void addError(String error) {
        errors.add(error);
    }

    @Override
    public String toString() {
        return "LineError{" +
                "fileName='" + fileName + '\'' +
                ", lineNumber=" + lineNumber +
                ", lineContent='" + lineContent + '\'' +
                ", errors=" + errors +
                '}';
    }

}
