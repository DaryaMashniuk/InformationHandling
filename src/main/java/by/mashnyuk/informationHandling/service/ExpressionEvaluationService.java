package by.mashnyuk.informationHandling.service;

public interface ExpressionEvaluationService {
    String evaluate(String expression);

    boolean isNumber(String token);

    boolean isOperator(String token);

    boolean isFunction(String token);

    String formatResult(double result);
}
