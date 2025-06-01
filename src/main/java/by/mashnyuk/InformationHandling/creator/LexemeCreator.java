package by.mashnyuk.InformationHandling.creator;

import by.mashnyuk.InformationHandling.entity.Lexeme;
import by.mashnyuk.InformationHandling.entity.Word;

public class LexemeCreator implements TextComponentCreator<Lexeme> {
    private final TextComponentCreator<Word> wordCreator = new WordCreator();

    @Override
    public Lexeme create(String text) {
        Lexeme lexeme = new Lexeme();
        lexeme.getChildren().add(wordCreator.create(text));
        return lexeme;
    }
}
