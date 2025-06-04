package by.mashnyuk.informationHandling.parser.impl;

import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.impl.Expression;
import by.mashnyuk.informationHandling.entity.impl.Lexeme;
import by.mashnyuk.informationHandling.parser.LexemeParser;
import by.mashnyuk.informationHandling.parser.TextParser;
import by.mashnyuk.informationHandling.service.ExpressionEvaluationService;
import by.mashnyuk.informationHandling.service.impl.ExpressionEvaluationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.*;

public class LexemeParserImpl extends TextParser implements LexemeParser {
    private static final String LEXEME_SPLIT_REGEX = "\\s+";
    private static final String EXPRESSION_REGEX =
            "(?<=\\W|^)(?<function>sin|cos|tan|sqrt)\\b|" +
                    "(?<number>-?\\d+(?:\\.\\d+)?)|" +
                    "(?<operator>[+\\-*/^])|" +
                    "(?<paren>[()])";
    private static final Character OPEN_PARENTHESIS = '(';
    private static final Character CLOSE_PARENTHESIS = ')';
    private static final Logger log = LogManager.getLogger();

    public LexemeParserImpl(TextParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        Lexeme resultLexeme = new Lexeme();
        Matcher matcher = Pattern.compile(EXPRESSION_REGEX).matcher(text);
        int lastIndex = 0;
        StringBuilder currentExpression = new StringBuilder();
        int parenLevel = 0;

        while (matcher.find()) {
            String token = matcher.group();
            String between = text.substring(lastIndex, matcher.start());

            if (!between.trim().isEmpty()) {
                if (parenLevel > 0) {
                    currentExpression.append(between);
                }
                else if (currentExpression.length() > 0) {
                    processExpression(currentExpression.toString(), resultLexeme);
                    currentExpression.setLength(0);
                    processRegularText(between.trim(), resultLexeme);
                } else {
                    processRegularText(between.trim(), resultLexeme);
                }
            }

            if (matcher.group("paren") != null) {
                if (matcher.group("paren").equals(OPEN_PARENTHESIS)) {
                    parenLevel++;
                } else {
                    parenLevel--;
                }
                currentExpression.append(token);
            }
            else if (matcher.group("function") != null ||
                    matcher.group("number") != null ||
                    matcher.group("operator") != null) {
                currentExpression.append(token);
            }

            lastIndex = matcher.end();
        }

        if (currentExpression.length() > 0) {
            processExpression(currentExpression.toString(), resultLexeme);
        }

        if (lastIndex < text.length()) {
            String remaining = text.substring(lastIndex).trim();
            if (!remaining.isEmpty()) {
                processRegularText(remaining, resultLexeme);
            }
        }

        return resultLexeme;
    }

    @Override
    public void processExpression(String expr, Lexeme resultLexeme) {
        if (!isBalanced(expr)) {
            processRegularText(expr, resultLexeme);
            return;
        }

        ExpressionEvaluationService evaluator = new ExpressionEvaluationServiceImpl();
        String evaluated = evaluator.evaluate(expr);

        Lexeme lexeme = new Lexeme();
        lexeme.add(new Expression(expr, evaluated));
        resultLexeme.add(lexeme);
    }

    @Override
    public boolean isBalanced(String expr) {
        int balance = 0;
        for (char c : expr.toCharArray()) {
            if (c == OPEN_PARENTHESIS) balance++;
            if (c == CLOSE_PARENTHESIS) balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    @Override
    public void processRegularText(String text, Lexeme resultLexeme) {
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