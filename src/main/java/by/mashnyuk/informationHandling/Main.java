package by.mashnyuk.informationHandling;

import by.mashnyuk.informationHandling.creator.TextCreator;
import by.mashnyuk.informationHandling.entity.Text;
import by.mashnyuk.informationHandling.io.TextFileReader;
import by.mashnyuk.informationHandling.service.TextAnalysisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            TextCreator creator = new TextCreator();
            TextAnalysisService service = new TextAnalysisService();
            TextFileReader reader = new TextFileReader();

            String fileName = "input.txt";
            List<String> lines = reader.readLines(fileName);
            String fullText = String.join("\n", lines);

            Text text = creator.createText(fullText);

            logger.info("=== Original Text ===");
            logger.info(text.getText());

            Text sortedText = service.sortParagraphsBySentenceCount(text);
            logger.info("\n=== Sorted Paragraphs ===");
            logger.info(sortedText.getText());

            logger.info("\n=== Sentences with Longest Word ===");
            service.findSentencesWithLongestWord(text).forEach(s ->
                    logger.info("- {}", s.getText()));

            logger.info("\n=== Filtered Text (min 5 words) ===");
            Text filteredText = service.removeShortSentences(text, 5);
            if (filteredText.getChildren().isEmpty()) {
                logger.info("No sentences with 5 or more words found");
            } else {
                logger.info(filteredText.getText());
            }

            logger.info("\n=== Duplicate Words ===");
            service.countDuplicateWords(text).forEach((word, count) ->
                    logger.info("- {}: {}", word, count));

            logger.info("\n=== Vowel/Consonant Counts ===");
            service.countVowelsAndConsonants(text).forEach((sentence, counts) ->
                    logger.info("- '{}'\n  Vowels: {}, Consonants: {}",
                            sentence.getText(), counts[0], counts[1]));

        } catch (Exception e) {
            logger.error("Application error: {}", e.getMessage());
        }
    }
}
