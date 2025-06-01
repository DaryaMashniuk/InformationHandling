package by.mashnyuk.InformationHandling.creator;

import by.mashnyuk.InformationHandling.entity.Symbol;
import by.mashnyuk.InformationHandling.entity.Word;

public class WordCreator implements TextComponentCreator<Word> {
    private final TextComponentCreator<Symbol> symbolCreator = new SymbolCreator();

    @Override
    public Word create(String text) {
        Word word = new Word();
        for (char c : text.toCharArray()) {
            word.getChildren().add(symbolCreator.create(String.valueOf(c)));
        }
        return word;
    }
}