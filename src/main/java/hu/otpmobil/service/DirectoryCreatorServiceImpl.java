package hu.otpmobil.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hu.otpmobil.config.ApplicationConstants.LOG_BASE_PATH;
import static hu.otpmobil.config.ApplicationConstants.REPORT_BASE_PATH;

public class DirectoryCreatorServiceImpl implements DirectoryCreatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryCreatorServiceImpl.class);
    @Override
    public void createOutputDirectories() {
        try {
            Path logDir = Paths.get(LOG_BASE_PATH);
            Path reportsDir = Paths.get(REPORT_BASE_PATH);

            Files.createDirectories(logDir);

            Files.createDirectories(reportsDir);
        } catch (IOException e) {
            LOGGER.error("Failed to create directories: " + e.getMessage());
        }
    }

}
