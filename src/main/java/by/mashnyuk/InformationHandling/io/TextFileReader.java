package by.mashnyuk.InformationHandling.io;

import by.mashnyuk.InformationHandling.exception.TextAnalysisException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class TextFileReader {
    private static final Logger logger = LogManager.getLogger(TextFileReader.class);

    public List<String> readLines(String fileName) throws TextAnalysisException {
        try {
            Path path = getFilePath(fileName);
            return Files.lines(path, StandardCharsets.UTF_8)
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
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

        // First try to find file directly
        if (Files.exists(path)) {
            return path;
        }

        // Try to find in resources folder
        Path resourcePath = Paths.get("src/main/resources", fileName);
        if (Files.exists(resourcePath)) {
            return resourcePath;
        }

        // Try to find in current directory
        Path currentDirPath = Paths.get(System.getProperty("user.dir"), fileName);
        if (Files.exists(currentDirPath)) {
            return currentDirPath;
        }

        throw new IOException("File not found in any searched location: " + fileName);
    }
}