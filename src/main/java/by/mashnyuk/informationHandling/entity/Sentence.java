package by.mashnyuk.informationHandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Sentence implements TextComponent {
    private List<TextComponent> children = new ArrayList<>();

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent lexeme : children) {
            sb.append(lexeme.getText()).append(" ");
        }
        return sb.toString().trim();
    }

    @Override
    public List<TextComponent> getChildren() {
        return children;
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }
}