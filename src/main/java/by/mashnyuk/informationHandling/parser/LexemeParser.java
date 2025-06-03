package by.mashnyuk.informationHandling.parser;

import by.mashnyuk.informationHandling.entity.*;
import by.mashnyuk.informationHandling.service.ExpressionEvaluationService;

import java.util.regex.*;

public class LexemeParser extends TextParser {
    private static final String LEXEME_SPLIT_REGEX = "\\s+";
    private static final String EXPRESSION_REGEX = "\\(([^()]+|\\([^()]+\\))*\\)";
  // Improved expression pattern

    public LexemeParser(TextParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        Lexeme resultLexeme = new Lexeme();
        Matcher matcher = Pattern.compile(EXPRESSION_REGEX).matcher(text);
        int lastIndex = 0;

        while (matcher.find()) {
            // Handle text before the expression
            if (matcher.start() > lastIndex) {
                String before = text.substring(lastIndex, matcher.start()).trim();
                if (!before.isEmpty()) {
                    processRegularText(before, resultLexeme);
                }
            }

            // Process the expression
            String expr = matcher.group();
            ExpressionEvaluationService evaluator = new ExpressionEvaluationService();
            String evaluated = evaluator.evaluate(expr);

            Lexeme lexeme = new Lexeme();
            lexeme.add(new Expression(expr, evaluated));
            resultLexeme.add(lexeme);

            lastIndex = matcher.end();
        }

        // Handle remaining text after last expression
        if (lastIndex < text.length()) {
            String remaining = text.substring(lastIndex).trim();
            if (!remaining.isEmpty()) {
                processRegularText(remaining, resultLexeme);
            }
        }

        return resultLexeme;
    }

    private void processRegularText(String text, Lexeme resultLexeme) {
        String[] words = text.split(LEXEME_SPLIT_REGEX);
        for (String word : words) {
            if (!word.isEmpty()) {
                Lexeme lexeme = new Lexeme();
                lexeme.add(nextParser.parse(word));
                resultLexeme.add(lexeme);
            }
        }
    }
}