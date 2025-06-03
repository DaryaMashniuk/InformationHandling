package by.mashnyuk.informationHandling.parser;

import by.mashnyuk.informationHandling.entity.*;

public abstract class TextParser {
    protected TextParser nextParser;

    public TextParser() {}

    public TextParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);
}