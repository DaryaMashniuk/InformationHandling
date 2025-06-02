package by.mashnyuk.informationHandling.io;

import by.mashnyuk.informationHandling.exception.TextAnalysisException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class TextFileReader {
    private static final Logger logger = LogManager.getLogger(TextFileReader.class);

    public List<String> readLines(String fileName) throws TextAnalysisException {
        try {
            Path path = getFilePath(fileName);
            return Files.lines(path, StandardCharsets.UTF_8)
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .toList();
        } catch (IOException e) {
            String errorMsg = "Failed to read file: " + fileName +
                    ". Current directory: " + System.getProperty("user.dir");
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

        if (Files.exists(path)) {
            return path;
        }

        Path resourcePath = Paths.get("src/main/resources", fileName);
        if (Files.exists(resourcePath)) {
            return resourcePath;
        }

        Path currentDirPath = Paths.get(System.getProperty("user.dir"), fileName);
        if (Files.exists(currentDirPath)) {
            return currentDirPath;
        }

        throw new IOException("File not found in any searched location: " + fileName);
    }
}