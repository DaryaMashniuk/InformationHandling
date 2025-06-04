package by.mashnyuk.informationHandling.parser;

import java.util.List;

public interface ExpressionParser {
    boolean isValid();

    List<String> parse(String infix);

    boolean isNumber(String token);

    boolean isOperator(String token);

    boolean isFunction(String token);

    int getPriority(String op);
}
