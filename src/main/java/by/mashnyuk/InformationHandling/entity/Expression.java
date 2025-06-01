package by.mashnyuk.InformationHandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Expression implements TextComponent {
    private final String expression;
    private final String value;

    public Expression(String expression, String value) {
        this.expression = expression;
        this.value = value;
    }

    @Override
    public String getText() {
        return value;
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>();
    }

    public String getExpression() {
        return expression;
    }
}
