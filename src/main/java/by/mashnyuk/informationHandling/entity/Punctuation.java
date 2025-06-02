package by.mashnyuk.informationHandling.entity;

public class Punctuation extends TextComponent {
    private final String symbol;

    public Punctuation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getText() {
        return symbol;
    }
}