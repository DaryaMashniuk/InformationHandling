package by.mashnyuk.informationHandling.entity.impl;

import by.mashnyuk.informationHandling.entity.AbstractTextComponent;
import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.TextComponentType;

import java.util.Collections;
import java.util.List;

public class Punctuation extends AbstractTextComponent {
    private String mark;

    public Punctuation(String mark) {
        this.mark = mark;
        this.type = TextComponentType.PUNCTUATION;
    }

    @Override
    public String getText() {
        return mark;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }
}