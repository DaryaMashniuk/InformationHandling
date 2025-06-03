package by.mashnyuk.informationHandling.entity.impl;

import by.mashnyuk.informationHandling.entity.AbstractTextComponent;
import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.TextComponentType;

import java.util.Collections;
import java.util.List;

public class Expression extends AbstractTextComponent {
    private String expression;
    private String value;

    public Expression(String expression, String value) {
        this.expression = expression;
        this.value = value;
        this.type = TextComponentType.EXPRESSION;
    }

    @Override
    public String getText() {
        return value;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }
}