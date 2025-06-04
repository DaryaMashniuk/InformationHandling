package by.mashnyuk.informationHandling.parser;

import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.impl.Lexeme;

public interface LexemeParser {
    TextComponent parse(String text);

    void processExpression(String expr, Lexeme resultLexeme);

    boolean isBalanced(String expr);

    void processRegularText(String text, Lexeme resultLexeme);
}
