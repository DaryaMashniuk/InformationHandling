package by.mashnyuk.informationHandling.creator;

import by.mashnyuk.informationHandling.entity.impl.Text;
import by.mashnyuk.informationHandling.parser.*;

public class TextCreator {
    private TextParser parserChain;

    public TextCreator() {
        this.parserChain = new ParagraphParser(
                new SentenceParser(
                        new LexemeParser(
                                new WordParser()
                        )
                )
        );
    }

    public Text createText(String rawText) {
        return (Text) parserChain.parse(rawText);
    }
}