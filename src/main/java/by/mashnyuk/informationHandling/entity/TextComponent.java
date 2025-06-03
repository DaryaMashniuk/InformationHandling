package by.mashnyuk.informationHandling.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    List<TextComponent> getChildren();
    String getText();
    TextComponentType getType();
}