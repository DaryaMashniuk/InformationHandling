package by.mashnyuk.informationHandling.parser;

import by.mashnyuk.informationHandling.entity.*;
import by.mashnyuk.informationHandling.service.ExpressionEvaluationService;

public class TextParser {
    private static final String PARAGRAPH_DELIMITER = "\\n\\s*\\n";
    private static final String SENTENCE_DELIMITER = "(?<=[.!?])\\s+";
    private static final String LEXEME_DELIMITER = "\\s+";
    private static final String EXPRESSION_PATTERN = "\\([^()]+\\)";
    private static final String WORD_PATTERN = "[a-zA-Zа-яА-ЯёЁ]+";
    private static final String PUNCTUATION_PATTERN = "[^a-zA-Zа-яА-ЯёЁ\\s]";

    public Text parse(String text) {
        Text result = new Text();
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);

        for (String paragraph : paragraphs) {
            if (!paragraph.trim().isEmpty()) {
                result.add(parseParagraph(paragraph.trim()));
            }
        }

        return result;
    }

    private Paragraph parseParagraph(String paragraphText) {
        Paragraph paragraph = new Paragraph();
        String[] sentences = paragraphText.split(SENTENCE_DELIMITER);

        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                paragraph.add(parseSentence(sentence.trim()));
            }
        }

        return paragraph;
    }

    private Sentence parseSentence(String sentenceText) {
        Sentence sentence = new Sentence();
        String[] lexemes = sentenceText.split(LEXEME_DELIMITER);

        for (String lexeme : lexemes) {
            if (!lexeme.trim().isEmpty()) {
                sentence.add(parseLexeme(lexeme.trim()));
            }
        }

        return sentence;
    }

    private Lexeme parseLexeme(String lexemeText) {
        Lexeme lexeme = new Lexeme();

        // Обработка выражений в скобках
        if (lexemeText.matches(EXPRESSION_PATTERN)) {
            Expression expression = new Expression(lexemeText,
                    new ExpressionEvaluationService().evaluate(lexemeText));
            lexeme.add(expression);
            return lexeme;
        }

        // Обработка слов и пунктуации
        String[] parts = lexemeText.split("(?=" + PUNCTUATION_PATTERN + ")|(?<=" + PUNCTUATION_PATTERN + ")");

        for (String part : parts) {
            if (part.matches(WORD_PATTERN)) {
                lexeme.add(new Word(part));
            } else if (part.matches(PUNCTUATION_PATTERN)) {
                lexeme.add(new Punctuation(part));
            }
        }

        return lexeme;
    }
}