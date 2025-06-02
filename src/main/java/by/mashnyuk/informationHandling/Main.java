package by.mashnyuk.informationHandling;

import by.mashnyuk.informationHandling.entity.Text;
import by.mashnyuk.informationHandling.io.TextFileReader;
import by.mashnyuk.informationHandling.parser.TextParser;
import by.mashnyuk.informationHandling.service.TextAnalysisService;
import by.mashnyuk.informationHandling.validator.TextValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            String fileName = "input.txt";
            int minWordCount = 5;

            TextValidator validator = new TextValidator();
            TextFileReader reader = new TextFileReader();
            TextParser parser = new TextParser();
            TextAnalysisService service = new TextAnalysisService();

            // Валидация
            if (!validator.isValidFileName(fileName)) {
                logger.error("Invalid file name: {}", fileName);
                return;
            }

            // Чтение файла
            List<String> lines = reader.readLines(fileName);
            if (lines.isEmpty()) {
                logger.error("File is empty or cannot be read: {}", fileName);
                return;
            }

            // Парсинг текста
            String fullText = String.join("\n", lines);
            Text text = parser.parse(fullText);

            // Обработка текста
            logger.info("=== Original Text ===");
            logger.info(text.getText());

            // Сортировка абзацев
            Text sortedText = service.sortParagraphsBySentenceCount(text);
            logger.info("\n=== Sorted Paragraphs ===");
            logger.info(sortedText.getText());

            // Поиск предложений с самым длинным словом
            logger.info("\n=== Sentences with Longest Word ===");
            service.findSentencesWithLongestWord(text).forEach(s ->
                    logger.info("- {}", s.getText()));

            // Фильтрация предложений
            Text filteredText = service.removeShortSentences(text, minWordCount);
            logger.info("\n=== Filtered Text (min {} words) ===", minWordCount);
            logger.info(filteredText.getText());

            // Поиск дубликатов слов
            logger.info("\n=== Duplicate Words ===");
            service.countDuplicateWords(text).forEach((word, count) ->
                    logger.info("- {}: {}", word, count));

            // Подсчет гласных и согласных
            logger.info("\n=== Vowel/Consonant Counts ===");
            service.countVowelsAndConsonants(text).forEach((sentence, counts) ->
                    logger.info("- '{}'\n  Vowels: {}, Consonants: {}",
                            sentence.getText(), counts[0], counts[1]));

        } catch (Exception e) {
            logger.error("Application error: {}", e.getMessage());
        }
    }
}