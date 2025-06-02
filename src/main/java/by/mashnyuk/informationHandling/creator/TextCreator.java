package by.mashnyuk.informationHandling.creator;


import by.mashnyuk.informationHandling.entity.Text;
import by.mashnyuk.informationHandling.parser.TextParser;

public class TextCreator {
    private final TextParser parser;

    public TextCreator() {
        this.parser = new TextParser(); // создаём парсер
    }

    public Text createText(String rawText) {
        return parser.parse(rawText); // делегируем парсинг парсеру
    }
}
