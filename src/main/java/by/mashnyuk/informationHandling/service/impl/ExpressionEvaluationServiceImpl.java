package by.mashnyuk.informationHandling.service.impl;

import by.mashnyuk.informationHandling.parser.ExpressionParser;
import by.mashnyuk.informationHandling.parser.impl.ExpressionParserImpl;
import by.mashnyuk.informationHandling.service.ExpressionEvaluationService;

import java.util.*;

public class ExpressionEvaluationServiceImpl implements ExpressionEvaluationService {
    private static final String NUMBER_PATTERN = "-?\\d+(\\.\\d+)?";
    private static final String WORD_PATTERN = "[a-zA-Z]+";
    private static final String OPERATOR_PATTERN = "[+\\-*/^u-]";
    private static final String FUNCTION_PATTERN = "(sin|cos|tan|sqrt)";

    @Override
    public String evaluate(String expression) {
        try {
            if (expression.matches(NUMBER_PATTERN)) {
                return expression;
            }

            if (expression.matches(WORD_PATTERN)) {
                return expression;
            }

            ExpressionParser parser = new ExpressionParserImpl();
            List<String> rpn = parser.parse(expression);

            if (!parser.isValid()) {
                return expression;
            }

            double result = evaluateRPN(rpn);
            return formatResult(result);
        } catch (Exception e) {
            return expression;
        }
    }

    private double evaluateRPN(List<String> rpn) {
        Deque<Double> stack = new ArrayDeque<>();

        for (String token : rpn) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (token.equals("u-")) {
                    stack.push(-stack.pop());
                } else {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Not enough operands for operator " + token);
                    }
                    double b = stack.pop();
                    double a = stack.pop();
                    switch (token) {
                        case "+": stack.push(a + b); break;
                        case "-": stack.push(a - b); break;
                        case "*": stack.push(a * b); break;
                        case "/": stack.push(a / b); break;
                        case "^": stack.push(Math.pow(a, b)); break;
                    }
                }
            } else if (isFunction(token)) {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("No argument for function " + token);
                }
                double arg = stack.pop();
                switch (token) {
                    case "sqrt": stack.push(Math.sqrt(arg)); break;
                    case "sin": stack.push(Math.sin(arg)); break;
                    case "cos": stack.push(Math.cos(arg)); break;
                    case "tan": stack.push(Math.tan(arg)); break;
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return stack.pop();
    }

    @Override
    public boolean isNumber(String token) {
        return token.matches(NUMBER_PATTERN);
    }

    @Override
    public boolean isOperator(String token) {
        return token.matches(OPERATOR_PATTERN);
    }

    @Override
    public boolean isFunction(String token) {
        return token.matches(FUNCTION_PATTERN);
    }

    @Override
    public String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }
}