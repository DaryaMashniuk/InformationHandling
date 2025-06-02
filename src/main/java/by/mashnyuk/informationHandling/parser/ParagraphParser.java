package by.mashnyuk.informationHandling.parser;

public class ParagraphParser {
    private static final String SENTENCE_DELIMITER = "(?<=[.!?])\\s*";

    private final SentenceParser sentenceParser = new SentenceParser();
//
//    public TextComponent parse(String paragraph) {
//        CompositeTextComponent paragraphComponent = new CompositeTextComponent();
//        String[] sentences = paragraph.split(SENTENCE_DELIMITER);
//
//        for (String sentence : sentences) {
//            paragraphComponent.add(sentenceParser.parse(sentence));
//        }
//
//        return paragraphComponent;
//    }
}

