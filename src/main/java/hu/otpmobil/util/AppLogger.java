package hu.otpmobil.util;

import hu.otpmobil.model.LineError;
import org.slf4j.LoggerFactory;

public class AppLogger {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AppLogger.class);
    public static void logLineError(LineError lineError) {
        LOGGER.error("An error occurred during read data from file!  DETAILS: {file : '{}', lineNumber : '{}', lineContent : '{}', errors : '{}'}",
                lineError.getFileName(), lineError.getLineNumber(), lineError.getLineContent(), lineError.getErrors());
    }

}
