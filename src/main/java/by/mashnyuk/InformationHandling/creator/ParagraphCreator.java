package by.mashnyuk.InformationHandling.creator;

import by.mashnyuk.InformationHandling.entity.Paragraph;
import by.mashnyuk.InformationHandling.entity.Sentence;

public class ParagraphCreator implements TextComponentCreator<Paragraph> {
    private final TextComponentCreator<Sentence> sentenceCreator = new SentenceCreator();

    @Override
    public Paragraph create(String text) {
        Paragraph paragraph = new Paragraph();
        paragraph.getChildren().add(sentenceCreator.create(text));
        return paragraph;
    }
}
