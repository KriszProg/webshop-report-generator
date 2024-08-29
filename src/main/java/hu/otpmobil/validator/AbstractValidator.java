package hu.otpmobil.validator;

import hu.otpmobil.model.FieldName;
import hu.otpmobil.model.LineError;
import hu.otpmobil.util.AppLogger;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;
import org.slf4j.Logger;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

public abstract class AbstractValidator {

    protected Logger logger;

    protected AbstractValidator(Logger logger) {
        this.logger = logger;
    }

    protected abstract List<String> validateLineDetailsAndCollectLineErrors(String line, Separator separator);

    public boolean isDataInvalid(String fileName, Integer lineNumber, String line, Separator separator,
                                 Integer requiredAmountOfData) {
        if (isDataSyntacticallyInvalid(fileName, lineNumber, line, separator, requiredAmountOfData)) {
            return true;
        }

        List<String> lineErrors = validateLineDetailsAndCollectLineErrors(line, separator);
        if (!lineErrors.isEmpty()) {
            LineError lineError = new LineError()
                    .fileName(fileName)
                    .lineNumber(lineNumber)
                    .lineContent(line)
                    .errors(lineErrors);
            AppLogger.logLineError(logger, lineError);
            return true;
        }

        return false;
    }

    private boolean isDataSyntacticallyInvalid(String fileName, Integer lineNumber, String line, Separator separator, Integer requiredAmountOfData) {
        if (isLineEmpty(line)) {
            return true;
        }
        if (!isLineSeparatorPresent(line, separator)) {
            AppLogger.logLineError(logger, new LineError()
                    .fileName(fileName)
                    .lineNumber(lineNumber)
                    .lineContent(line)
                    .errors(Collections.singletonList(Message.EXPECTED_SEPARATOR_NOT_PRESENT.getMessage())));
            return true;
        }
        if (!isLineContainsDataInRequiredAmount(line, separator, requiredAmountOfData)) {
            AppLogger.logLineError(logger, new LineError()
                    .fileName(fileName)
                    .lineNumber(lineNumber)
                    .lineContent(line)
                    .errors(Collections.singletonList(MessageFormat.format(
                            Message.INVALID_LINE_CONTENT.getMessage(), requiredAmountOfData))));
            return true;
        }

        return false;
    }

    private boolean isLineEmpty(String line) {
        return line.trim().isEmpty();
    }

    private boolean isLineSeparatorPresent(String line, Separator separator) {
        return line.contains(separator.getSeparatorString());
    }

    private boolean isLineContainsDataInRequiredAmount(String line, Separator separator, Integer requiredAmountOfData) {
        return line.split(separator.getSeparatorRegex()).length == requiredAmountOfData;
    }

    protected void validateDataIsNotEmptyAndAddError(String data, FieldName fieldName, List<String> lineErrors) {
        if (data.isEmpty()) {
            addErrorForEmptyData(fieldName, lineErrors);
        }
    }

    protected void addErrorForEmptyData(FieldName fieldName, List<String> lineErrors) {
        lineErrors.add(MessageFormat.format(Message.DATA_CANNOT_BE_EMPTY.getMessage(), fieldName.getName()));
    }

}
