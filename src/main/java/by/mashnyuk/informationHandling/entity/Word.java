package by.mashnyuk.informationHandling.entity;

import java.util.Collections;
import java.util.List;

public class Word implements TextComponent {
    private String text;

    public Word(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Word cannot have children");
    }
}