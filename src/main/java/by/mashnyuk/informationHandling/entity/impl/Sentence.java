package by.mashnyuk.informationHandling.entity.impl;

import by.mashnyuk.informationHandling.entity.AbstractTextComponent;
import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.TextComponentType;

import java.util.stream.Collectors;

public class Sentence extends AbstractTextComponent {
    public Sentence() {
        this.type = TextComponentType.SENTENCE;
    }

    @Override
    public String getText() {
        return children.stream()
                .map(TextComponent::getText)
                .collect(Collectors.joining(" "));
    }
}