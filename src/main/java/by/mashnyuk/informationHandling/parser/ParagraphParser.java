package by.mashnyuk.informationHandling.parser;

import by.mashnyuk.informationHandling.entity.*;

public class ParagraphParser extends TextParser {
    private static final String PARAGRAPH_SPLIT_REGEX = "\\n\\s*(?=\t|    )";

    public ParagraphParser(TextParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        Text resultText = new Text();
        String[] paragraphs = text.split(PARAGRAPH_SPLIT_REGEX);

        for (String para : paragraphs) {
            if (!para.trim().isEmpty()) {
                Paragraph paragraph = new Paragraph();
                TextComponent parsed = nextParser.parse(para.trim());
                if (parsed instanceof Sentence) {
                    for (TextComponent sentence : parsed.getChildren()) {
                        paragraph.add(sentence);
                    }
                }
                resultText.add(paragraph);
            }
        }

        return resultText;
    }
}