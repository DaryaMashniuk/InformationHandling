package by.mashnyuk.InformationHandling.creator;

import by.mashnyuk.InformationHandling.entity.Paragraph;
import by.mashnyuk.InformationHandling.entity.Text;

public class TextCreator implements TextComponentCreator<Text> {
    private final TextComponentCreator<Paragraph> paragraphCreator = new ParagraphCreator();

    @Override
    public Text create(String text) {
        Text result = new Text();
        result.getChildren().add(paragraphCreator.create(text));
        return result;
    }
}