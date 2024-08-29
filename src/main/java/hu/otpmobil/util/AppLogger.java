package hu.otpmobil.util;

import hu.otpmobil.model.LineError;
import org.slf4j.Logger;

import static hu.otpmobil.util.Message.DATA_PROCESSING_ERROR;

public class AppLogger {

    public static void logLineError(Logger logger, LineError lineError) {
        logger.error(DATA_PROCESSING_ERROR.getMessage(),
                lineError.getFileName(), lineError.getLineNumber(), lineError.getLineContent(), lineError.getErrors());
    }

}
