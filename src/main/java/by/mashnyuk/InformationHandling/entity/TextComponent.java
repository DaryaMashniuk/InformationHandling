package by.mashnyuk.InformationHandling.entity;

import java.util.List;

public interface TextComponent {
    String getText();
    List<TextComponent> getChildren();
}