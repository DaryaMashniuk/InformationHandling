package by.mashnyuk.informationHandling.parser.impl;

import by.mashnyuk.informationHandling.entity.*;
import by.mashnyuk.informationHandling.entity.impl.Paragraph;
import by.mashnyuk.informationHandling.entity.impl.Sentence;
import by.mashnyuk.informationHandling.entity.impl.Text;
import by.mashnyuk.informationHandling.parser.TextParser;
import by.mashnyuk.informationHandling.validator.TextValidator;
import by.mashnyuk.informationHandling.validator.impl.TextValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ParagraphParser extends TextParser {
    private static final String PARAGRAPH_SPLIT_REGEX = "\\n\\s*(?=\t|    )";
    private static final Logger log = LogManager.getLogger();
    private final TextValidator validator = new TextValidatorImpl();

    public ParagraphParser(TextParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        Text resultText = new Text();

        if (!validator.isValidText(resultText)) {
            log.warn("Skipping empty or invalid paragraph");
        }

        int wordCount = countWords(resultText);
        if (!validator.isValidWordCountThreshold(wordCount)) {
            log.warn("Skipping paragraph with invalid word count ({} words): {}", wordCount, resultText);
        }
        String[] paragraphs = text.split(PARAGRAPH_SPLIT_REGEX);

        log.debug("paragraphs: {}", paragraphs);
        for (String para : paragraphs) {
            if (!para.trim().isEmpty()) {
                Paragraph paragraph = new Paragraph();
                TextComponent parsed = nextParser.parse(para.trim());
                if (parsed instanceof Sentence) {
                    for (TextComponent sentence : parsed.getChildren()) {
                        paragraph.add(sentence);
                    }
                }
                resultText.add(paragraph);
            }
        }

        return resultText;
    }

    private int countWords(Text text) {
        if (text == null || text.toString().isEmpty()) return 0;
        return text.toString().trim().split("\\s+").length;
    }
}