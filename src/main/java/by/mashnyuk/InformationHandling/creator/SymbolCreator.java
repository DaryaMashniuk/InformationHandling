package by.mashnyuk.InformationHandling.creator;

import by.mashnyuk.InformationHandling.entity.Symbol;

public class SymbolCreator implements TextComponentCreator<Symbol> {
    @Override
    public Symbol create(String text) {
        return new Symbol(text.charAt(0));
    }
}
