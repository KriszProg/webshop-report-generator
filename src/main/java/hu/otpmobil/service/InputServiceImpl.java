package hu.otpmobil.service;

import hu.otpmobil.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputServiceImpl implements InputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputServiceImpl.class);
    private final Scanner scanner;

    public InputServiceImpl() {
        this.scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    @Override
    public String getValidFilePathFromUser(Message message) {
        String filePath;

        while (true) {
            System.out.println(message.getMessage());

            filePath = scanner.nextLine().trim();
            Path path = Paths.get(filePath);

            if (!Files.isRegularFile(path) || !Files.exists(path)) {
                LOGGER.error(Message.INVALID_FILE_PATH.getMessage());
            } else {
                break;
            }
        }

        return filePath;
    }

}
