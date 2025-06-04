package by.mashnyuk.informationHandling.io;

import by.mashnyuk.informationHandling.exception.TextAnalysisException;
import by.mashnyuk.informationHandling.validator.TextValidator;
import by.mashnyuk.informationHandling.validator.impl.TextValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class TextFileReader {
    private static final Logger logger = LogManager.getLogger(TextFileReader.class);
    private static final String RESOURCE_PATH = "src/main/resources";
    private static final String USER_PATH = "user.dir";

    private final TextValidator validator = new TextValidatorImpl();

    public List<String> readLines(String fileName) throws TextAnalysisException {
        if (!validator.isValidFileName(fileName)) {
            throw new TextAnalysisException(
                    "Invalid file name format: " + fileName,
                    TextAnalysisException.ErrorType.VALIDATION_ERROR
            );
        }

        try {
            Path path = getFilePath(fileName);

            long fileSize = Files.size(path);
            if (!validator.isValidFileSize(fileSize)) {
                throw new TextAnalysisException(
                        "File size exceeds allowed limit: " + fileSize + " bytes",
                        TextAnalysisException.ErrorType.VALIDATION_ERROR
                );
            }

            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.removeIf(line -> line.trim().isEmpty());

            if (lines.isEmpty()) {
                throw new TextAnalysisException(
                        "File is empty or contains only whitespace.",
                        TextAnalysisException.ErrorType.VALIDATION_ERROR
                );
            }

            return lines;

        } catch (IOException e) {
            String errorMsg = "Failed to read file: " + fileName +
                    ". Current directory: " + System.getProperty(USER_PATH);
            logger.error(errorMsg, e);
            throw new TextAnalysisException(
                    errorMsg,
                    TextAnalysisException.ErrorType.IO_ERROR,
                    e
            );
        }
    }

    private Path getFilePath(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (Files.exists(path)) return path;

        Path resourcePath = Paths.get(RESOURCE_PATH, fileName);
        if (Files.exists(resourcePath)) return resourcePath;

        Path currentDirPath = Paths.get(System.getProperty(USER_PATH), fileName);
        if (Files.exists(currentDirPath)) return currentDirPath;

        throw new IOException("File not found in any searched location: " + fileName);
    }
}
