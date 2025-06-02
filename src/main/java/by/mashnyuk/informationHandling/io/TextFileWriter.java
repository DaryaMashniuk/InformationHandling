package by.mashnyuk.informationHandling.io;

import by.mashnyuk.informationHandling.exception.TextAnalysisException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileWriter {
    private static final Logger logger = LogManager.getLogger(TextFileWriter.class);

    public void writeToFile(String fileName, String content) throws TextAnalysisException {
        Path path = Paths.get(fileName);

        try {
            Files.writeString(path, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Failed to write to file: {}", fileName, e);
            throw new TextAnalysisException(
                    "Failed to write to file: " + fileName,
                    TextAnalysisException.ErrorType.IO_ERROR,
                    e
            );
        }
    }
}
