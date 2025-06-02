package by.mashnyuk.informationHandling.entity;

public class Expression extends TextComponent {
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
}
