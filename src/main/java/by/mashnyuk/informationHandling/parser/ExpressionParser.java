package by.mashnyuk.informationHandling.parser;

import java.util.*;

public class ExpressionParser {
    private static final String OPERATORS = "+-*/";
    private static final String DELIMITERS = "()" + OPERATORS;
    public static boolean flag = true;

    private static boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        return DELIMITERS.contains(token);
    }

    private static boolean isOperator(String token) {
        return token.equals("u-") || OPERATORS.contains(token);
    }

    private static int priority(String token) {
        switch (token) {
            case "(": return 1;
            case "+": case "-": return 2;
            case "*": case "/": return 3;
            default: return 4;
        }
    }

    public static List<String> parse(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, DELIMITERS, true);
        String prev = "";
        String curr;

        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                flag = false;
                return postfix;
            }
            if (curr.equals(" ")) continue;

            if (isDelimiter(curr)) {
                if (curr.equals("(")) {
                    stack.push(curr);
                } else if (curr.equals(")")) {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                    }
                    if (stack.isEmpty()) {
                        flag = false;
                        return postfix;
                    }
                    stack.pop();
                } else {
                    if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev) && !prev.equals(")")))) {
                        curr = "u-";
                    } else {
                        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                            postfix.add(stack.pop());
                        }
                    }
                    stack.push(curr);
                }
            } else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
            else {
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }
}
