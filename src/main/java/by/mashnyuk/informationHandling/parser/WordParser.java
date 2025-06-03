package by.mashnyuk.informationHandling.parser;

import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.impl.Lexeme;
import by.mashnyuk.informationHandling.entity.impl.Punctuation;
import by.mashnyuk.informationHandling.entity.impl.Word;

public class WordParser extends TextParser {
    private static final String WORD_REGEX = "[a-zA-Zа-яА-ЯёЁ]+";
    private static final String PUNCTUATION_REGEX = "[^a-zA-Zа-яА-ЯёЁ\\s]";
    private static final String SPLIT_REGEX = "(?<=[" + PUNCTUATION_REGEX + "])|(?=[" + PUNCTUATION_REGEX + "])";

    public WordParser() {
        super(null);
    }

    @Override
    public TextComponent parse(String text) {
        Lexeme lexeme = new Lexeme();
        String[] parts = text.split(SPLIT_REGEX);

        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (part.matches(WORD_REGEX)) {
                lexeme.add(new Word(part));
            } else if (part.matches(PUNCTUATION_REGEX)) {
                lexeme.add(new Punctuation(part));
            }
        }

        return lexeme;
    }
}