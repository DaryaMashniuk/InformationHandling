package by.mashnyuk.informationHandling.entity.impl;

import by.mashnyuk.informationHandling.entity.AbstractTextComponent;
import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.TextComponentType;

import java.util.Collections;
import java.util.List;

public class Word extends AbstractTextComponent {
    private String word;

    public Word(String word) {
        this.word = word;
        this.type = TextComponentType.WORD;
    }

    @Override
    public String getText() {
        return word;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }
}