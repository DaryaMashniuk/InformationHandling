package by.mashnyuk.informationHandling.parser;

import by.mashnyuk.informationHandling.entity.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// SentenceParser.java — переписанный с использованием Lexeme
public class SentenceParser {
    private static final String TOKEN_PATTERN = "\\w+|[^\\w\\s]";
    private final Pattern tokenPattern = Pattern.compile(TOKEN_PATTERN);

//    public TextComponent parse(String sentence) {
//        Lexeme lexeme = new Lexeme();
//        CompositeTextComponent sentenceComponent = new Sentence();
//
//        Matcher matcher = tokenPattern.matcher(sentence);
//
//        while (matcher.find()) {
//            String token = matcher.group();
//            if (token.matches("\\w+")) {
//                lexeme = new Lexeme();
//                lexeme.add(new Word(token));
//            } else {
//                lexeme.add(new Punctuation(token));
//                sentenceComponent.add(lexeme);
//                lexeme = new Lexeme(); // reset after punctuation
//            }
//
//            if (!sentenceComponent.getChildren().contains(lexeme)) {
//                sentenceComponent.add(lexeme);
//            }
//        }
//
//        return sentenceComponent;
//    }
}
