package by.mashnyuk.InformationHandling.service;

import java.util.*;

public class ExpressionEvaluationService {

    public String evaluate(String expression) {
        try {
            // Remove parentheses
            String cleanExpr = expression.substring(1, expression.length() - 1);

            // Convert to postfix notation
            List<String> postfix = ExpressionParser.parse(cleanExpr);

            // Evaluate postfix expression
            return String.valueOf(ExpressionEvaluator.evaluate(postfix));
        } catch (Exception e) {
            return expression; // Return original if evaluation fails
        }
    }

    private static class ExpressionParser {
        private static final String OPERATORS = "+-*/^";
        private static final String DELIMITERS = "()" + OPERATORS;

        public static List<String> parse(String infix) {
            List<String> postfix = new ArrayList<>();
            Deque<String> stack = new ArrayDeque<>();
            StringTokenizer tokenizer = new StringTokenizer(infix, DELIMITERS, true);
            String prev = "";
            String curr;

            while (tokenizer.hasMoreTokens()) {
                curr = tokenizer.nextToken();

                if (curr.equals(" ")) continue;

                if (isDelimiter(curr)) {
                    if (curr.equals("(")) {
                        stack.push(curr);
                    } else if (curr.equals(")")) {
                        while (!stack.peek().equals("(")) {
                            postfix.add(stack.pop());
                        }
                        stack.pop();
                    } else {
                        if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev) && !prev.equals(")")))) {
                            curr = "u-";
                        }
                        while (!stack.isEmpty() && priority(curr) <= priority(stack.peek())) {
                            postfix.add(stack.pop());
                        }
                        stack.push(curr);
                    }
                } else {
                    postfix.add(curr);
                }
                prev = curr;
            }

            while (!stack.isEmpty()) {
                postfix.add(stack.pop());
            }

            return postfix;
        }

        private static boolean isDelimiter(String token) {
            if (token.length() != 1) return false;
            return DELIMITERS.contains(token);
        }

        private static int priority(String operator) {
            switch (operator) {
                case "(": return 0;
                case "+": case "-": return 1;
                case "*": case "/": return 2;
                case "^": return 3;
                case "u-": return 4;
                default: return 5;
            }
        }
    }

    private static class ExpressionEvaluator {
        public static double evaluate(List<String> postfix) {
            Deque<Double> stack = new ArrayDeque<>();

            for (String token : postfix) {
                if (token.equals("+")) {
                    stack.push(stack.pop() + stack.pop());
                } else if (token.equals("-")) {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                } else if (token.equals("*")) {
                    stack.push(stack.pop() * stack.pop());
                } else if (token.equals("/")) {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a / b);
                } else if (token.equals("^")) {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(Math.pow(a, b));
                } else if (token.equals("u-")) {
                    stack.push(-stack.pop());
                } else {
                    stack.push(Double.parseDouble(token));
                }
            }

            return stack.pop();
        }
    }
}
