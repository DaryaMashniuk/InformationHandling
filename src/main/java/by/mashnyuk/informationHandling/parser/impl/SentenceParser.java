package by.mashnyuk.informationHandling.parser.impl;

import by.mashnyuk.informationHandling.entity.*;
import by.mashnyuk.informationHandling.entity.impl.Lexeme;
import by.mashnyuk.informationHandling.entity.impl.Sentence;
import by.mashnyuk.informationHandling.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends TextParser {
    private static final String SENTENCE_SPLIT_REGEX = "(?<=[.!?])\\s+(?=[A-ZА-Я])";
    private static final Logger log = LogManager.getLogger();
    public SentenceParser(TextParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        Sentence resultSentence = new Sentence();
        String[] sentences = text.split(SENTENCE_SPLIT_REGEX);
        log.debug("Sentences {}", sentences);
        for (String sent : sentences) {
            if (!sent.trim().isEmpty()) {
                TextComponent parsed = nextParser.parse(sent.trim());
                if (parsed instanceof Lexeme) {
                    Sentence sentence = new Sentence();
                    for (TextComponent lexeme : parsed.getChildren()) {
                        sentence.add(lexeme);
                    }
                    resultSentence.add(sentence);
                }
            }
        }

        return resultSentence;
    }
}