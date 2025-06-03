package by.mashnyuk.informationHandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Text implements TextComponent {
    private List<TextComponent> children = new ArrayList<>();

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent paragraph : children) {
            sb.append(paragraph.getText()).append("\n\n");
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