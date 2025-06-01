package by.mashnyuk.InformationHandling;

import by.mashnyuk.InformationHandling.creator.TextCreator;
import by.mashnyuk.InformationHandling.entity.Sentence;
import by.mashnyuk.InformationHandling.entity.Text;
import by.mashnyuk.InformationHandling.io.TextFileReader;
import by.mashnyuk.InformationHandling.service.ExpressionEvaluationService;
import by.mashnyuk.InformationHandling.service.TextAnalysisService;
import by.mashnyuk.InformationHandling.validator.TextValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            String fileName = "input.txt";
            int minWordCount = 5; // Пример параметра для фильтрации предложений

            // Initialize components
            TextValidator validator = new TextValidator();
            TextFileReader reader = new TextFileReader();
            TextCreator creator = new TextCreator();
            ExpressionEvaluationService exprService = new ExpressionEvaluationService();
            TextAnalysisService service = new TextAnalysisService(exprService);

            // Validate and read file
            if (!validator.isValidFileName(fileName)) {
                logger.error("Invalid file name: {}", fileName);
                return;
            }

            if (!validator.isValidWordCountThreshold(minWordCount)) {
                logger.error("Invalid word count threshold: {}", minWordCount);
                return;
            }

            List<String> lines = reader.readLines(fileName);
            if (lines.isEmpty()) {
                logger.warn("File is empty: {}", fileName);
                return;
            }

            // Create text structure
            Text text = creator.create(String.join("\n", lines));

            // Perform analysis operations
            logger.info("=== Original Text ===");
            logger.info(text.getText());

            // 1. Sort paragraphs by sentence count
            Text sortedText = service.sortParagraphsBySentenceCount(text);
            logger.info("\n=== Sorted Paragraphs ===");
            logger.info(sortedText.getText());

            // 2. Find sentences with longest word
            List<Sentence> longWordSentences = service.findSentencesWithLongestWord(text);
            logger.info("\n=== Sentences with Longest Word ===");
            longWordSentences.forEach(s -> logger.info("- " + s.getText()));

            // 3. Remove short sentences
            Text filteredText = service.removeShortSentences(text, minWordCount);
            logger.info("\n=== Filtered Text (min {} words) ===", minWordCount);
            logger.info(filteredText.getText());

            // 4. Count duplicate words
            Map<String, Integer> duplicates = service.countDuplicateWords(text);
            logger.info("\n=== Duplicate Words ===");
            duplicates.forEach((word, count) ->
                    logger.info("- {}: {}", word, count));

            // 5. Count vowels and consonants
            Map<Sentence, int[]> vowelConsonantCounts =
                    service.countVowelsAndConsonants(text);
            logger.info("\n=== Vowel/Consonant Counts ===");
            vowelConsonantCounts.forEach((sentence, counts) ->
                    logger.info("- '{}'\n  Vowels: {}, Consonants: {}",
                            sentence.getText(), counts[0], counts[1]));

        } catch (Exception e) {
            logger.error("Application error", e);
        }
    }
}