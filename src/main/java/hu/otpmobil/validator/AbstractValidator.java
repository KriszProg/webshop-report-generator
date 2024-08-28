package hu.otpmobil.validator;

import hu.otpmobil.model.LineError;
import hu.otpmobil.model.UniqueId;
import hu.otpmobil.util.AppLogger;
import hu.otpmobil.util.Message;
import hu.otpmobil.util.Separator;

import java.text.MessageFormat;
import java.util.List;

public abstract class AbstractValidator {

    protected AbstractValidator() {}

    protected boolean isPaymentDataSyntacticallyValid(String fileName, Integer lineNumber, String line, Separator separator, Integer requiredAmountOfData) {
        if (isLineEmpty(line)) {
            return false;
        }
        if (!isLineSeparatorPresent(line, separator)) {
            LineError lineError = new LineError().fileName(fileName).lineNumber(lineNumber).lineContent(line);
            lineError.addError(Message.EXPECTED_SEPARATOR_NOT_PRESENT.getMessage());
            AppLogger.logLineError(lineError);
            return false;
        }
        if (!isLineContainsDataInRequiredAmount(line, separator, requiredAmountOfData)) {
            LineError lineError = new LineError().fileName(fileName).lineNumber(lineNumber).lineContent(line);
            lineError.addError(MessageFormat.format(Message.INVALID_LINE_CONTENT.getMessage(), requiredAmountOfData));
            AppLogger.logLineError(lineError);
            return false;
        }

        return true;
    }

    protected boolean isUniqueIdExists(UniqueId uniqueId, List<UniqueId> uniqueIdList) {
        return uniqueIdList.contains(uniqueId);
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

}
