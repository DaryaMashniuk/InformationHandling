package by.mashnyuk.informationHandling.service;

import by.mashnyuk.informationHandling.parser.ExpressionParser;

import java.util.*;

public class ExpressionEvaluationService {
    public String evaluate(String expression) {
        try {
            // Handle simple numbers
            if (expression.matches("-?\\d+(\\.\\d+)?")) {
                return expression;
            }

            // Handle simple words (like "five")
            if (expression.matches("[a-zA-Z]+")) {
                return expression;
            }

            // Convert to RPN and evaluate
            ExpressionParser parser = new ExpressionParser();
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
                    // Unary minus
                    stack.push(-stack.pop());
                } else {
                    // Binary operator
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

    private boolean isNumber(String token) {
        return token.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isOperator(String token) {
        return token.matches("[+\\-*/^u-]");
    }

    private boolean isFunction(String token) {
        return token.matches("(sin|cos|tan|sqrt)");
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }
}