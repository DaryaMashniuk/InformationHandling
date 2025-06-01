package by.mashnyuk.InformationHandling.creator;

import by.mashnyuk.InformationHandling.entity.Lexeme;
import by.mashnyuk.InformationHandling.entity.Sentence;

public class SentenceCreator implements TextComponentCreator<Sentence> {
    private final TextComponentCreator<Lexeme> lexemeCreator = new LexemeCreator();

    @Override
    public Sentence create(String text) {
        Sentence sentence = new Sentence();
        sentence.getChildren().add(lexemeCreator.create(text));
        return sentence;
    }
}
