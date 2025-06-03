package by.mashnyuk.informationHandling.entity;

import java.util.Collections;
import java.util.List;

public class Punctuation implements TextComponent {
    private String text;

    public Punctuation(String text) {
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
        throw new UnsupportedOperationException("Punctuation cannot have children");
    }
}