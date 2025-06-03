package by.mashnyuk.informationHandling.parser;

import java.util.*;

public class ExpressionParser {
    private static final String OPERATORS = "+-*/^";
    private static final String FUNCTIONS = "sin|cos|tan|sqrt";
    private static final String NUMBER = "-?\\d+(\\.\\d+)?";
    private static final String TOKENIZE_REGEX = "([+\\-*/^()])";
    private boolean isValid = true;

    public boolean isValid() {
        return isValid;
    }

    public List<String> parse(String infix) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        StringTokenizer tokenizer = new StringTokenizer(
                infix.replaceAll(TOKENIZE_REGEX, " $1 "),
                " ", true);

        String prevToken = "";

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (isNumber(token)) {
                output.add(token);
            }
            else if (isFunction(token)) {
                stack.push(token);
            }
            else if (token.equals("(")) {
                stack.push(token);
            }
            else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (stack.isEmpty()) {
                    isValid = false;
                    return output;
                }
                stack.pop();
                if (!stack.isEmpty() && isFunction(stack.peek())) {
                    output.add(stack.pop());
                }
            }
            else if (isOperator(token)) {

                if (token.equals("-") && (prevToken.isEmpty() ||
                        prevToken.equals("(") || isOperator(prevToken))) {
                    token = "u-";
                }

                while (!stack.isEmpty() && isOperator(stack.peek()) &&
                        getPriority(token) <= getPriority(stack.peek())) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }

            prevToken = token;
        }

        while (!stack.isEmpty()) {
            String op = stack.pop();
            if (op.equals("(")) {
                isValid = false;
                return output;
            }
            output.add(op);
        }

        return output;
    }

    private boolean isNumber(String token) {
        return token.matches(NUMBER);
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token) || token.equals("u-");
    }

    private boolean isFunction(String token) {
        return token.matches(FUNCTIONS);
    }

    private int getPriority(String op) {
        switch (op) {
            case "^": return 4;
            case "u-": return 4;
            case "*":
            case "/": return 3;
            case "+":
            case "-": return 2;
            default: return 0;
        }
    }
}