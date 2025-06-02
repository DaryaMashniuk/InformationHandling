package by.mashnyuk.informationHandling.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class TextComponent {
    protected List<TextComponent> children = new ArrayList<>();

    public void add(TextComponent component) {
        children.add(component);
    }

    public List<TextComponent> getChildren() {
        return children;
    }

    public abstract String getText();
}
