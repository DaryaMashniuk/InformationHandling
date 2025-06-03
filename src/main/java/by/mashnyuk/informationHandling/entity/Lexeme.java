package by.mashnyuk.informationHandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Lexeme implements TextComponent {
    private List<TextComponent> children = new ArrayList<>();

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent component : children) {
            sb.append(component.getText());
        }
        return sb.toString();
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