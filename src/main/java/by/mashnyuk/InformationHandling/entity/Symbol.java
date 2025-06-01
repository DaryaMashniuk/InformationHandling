package by.mashnyuk.InformationHandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Symbol implements TextComponent {
    private final char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getText() {
        return String.valueOf(symbol);
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>();
    }
}