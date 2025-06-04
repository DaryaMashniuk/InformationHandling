package by.mashnyuk.informationHandling.creator;

import by.mashnyuk.informationHandling.entity.impl.Text;
import by.mashnyuk.informationHandling.parser.*;
import by.mashnyuk.informationHandling.parser.impl.LexemeParserImpl;
import by.mashnyuk.informationHandling.parser.impl.ParagraphParser;
import by.mashnyuk.informationHandling.parser.impl.SentenceParser;
import by.mashnyuk.informationHandling.parser.impl.WordParser;

public class TextCreator {
    private TextParser parserChain;

    public TextCreator() {
        this.parserChain = new ParagraphParser(
                new
                        SentenceParser(
                        new LexemeParserImpl(
                                new WordParser()
                        )
                )
        );
    }

    public Text createText(String rawText) {
        return (Text) parserChain.parse(rawText);
    }
}