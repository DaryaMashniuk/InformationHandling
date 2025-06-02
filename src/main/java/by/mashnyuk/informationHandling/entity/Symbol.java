package by.mashnyuk.informationHandling.entity;


import java.util.Collections;
import java.util.List;

public class Symbol extends TextComponent {
    private final char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Cannot add to Symbol.");
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String getText() {
        return String.valueOf(symbol);
    }
}
