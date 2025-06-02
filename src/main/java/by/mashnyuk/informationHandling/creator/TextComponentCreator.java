package by.mashnyuk.informationHandling.creator;

import by.mashnyuk.informationHandling.entity.TextComponent;

public interface TextComponentCreator<T extends TextComponent> {
    T create(String text);
}