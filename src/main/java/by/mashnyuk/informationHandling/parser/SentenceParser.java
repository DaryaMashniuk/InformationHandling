package by.mashnyuk.informationHandling.parser;

import by.mashnyuk.informationHandling.entity.*;

public class SentenceParser extends TextParser {
    private static final String SENTENCE_SPLIT_REGEX = "(?<=[.!?])\\s+(?=[A-ZА-Я])";

    public SentenceParser(TextParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        Sentence resultSentence = new Sentence();
        String[] sentences = text.split(SENTENCE_SPLIT_REGEX);

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