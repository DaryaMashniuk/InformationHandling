package by.mashnyuk.informationHandling.entity;

import java.util.List;

public interface TextComponent {
    String getText();
    List<TextComponent> getChildren();
    void add(TextComponent component);
}