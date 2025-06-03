package by.mashnyuk.informationHandling.service;

import by.mashnyuk.informationHandling.parser.ExpressionParser;

import java.util.*;

import java.util.*;

public class ExpressionEvaluationService {
    public String evaluate(String expression) {
        try {
            // Remove outer parentheses if present
            String cleanExpr = expression.startsWith("(") && expression.endsWith(")")
                    ? expression.substring(1, expression.length() - 1)
                    : expression;

            // Handle simple numbers
            if (cleanExpr.matches("-?\\d+")) {
                return cleanExpr;
            }

            // Handle simple words (like "five")
            if (cleanExpr.matches("[a-zA-Z]+")) {
                return cleanExpr; // or implement word-to-number conversion
            }

            ExpressionParser parser = new ExpressionParser();
            List<String> postfix = parser.parse(cleanExpr);

            if (!parser.flag) {
                return expression;
            }

            double result = calc(postfix);

            if (result == (long) result) {
                return String.valueOf((long) result);
            }
            return String.valueOf(result);
        } catch (Exception e) {
            return expression;
        }
    }

    private double calc(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<Double>();
        for (String x : postfix) {
            if (x.equals("+")) stack.push(stack.pop() + stack.pop());
            else if (x.equals("-")) {
                Double b = stack.pop(), a = stack.pop();
                stack.push(a - b);
            }
            else if (x.equals("*")) stack.push(stack.pop() * stack.pop());
            else if (x.equals("/")) {
                Double b = stack.pop(), a = stack.pop();
                stack.push(a / b);
            }
            else if (x.equals("u-")) stack.push(-stack.pop());
            else stack.push(Double.valueOf(x));
        }
        return stack.pop();
    }
}