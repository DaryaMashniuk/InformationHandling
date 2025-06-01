package by.mashnyuk.InformationHandling.creator;

import by.mashnyuk.InformationHandling.entity.TextComponent;

public interface TextComponentCreator<T extends TextComponent> {
    T create(String text);
}