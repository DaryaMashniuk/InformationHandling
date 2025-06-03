package by.mashnyuk.informationHandling.entity;

import java.util.Collections;
import java.util.List;

public class Expression implements TextComponent {
    private String original;
    private String evaluated;

    public Expression(String original, String evaluated) {
        this.original = original;
        this.evaluated = evaluated;
    }

    @Override
    public String getText() {
        return evaluated;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Expression cannot have children");
    }
}