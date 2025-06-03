package by.mashnyuk.informationHandling.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextComponent implements TextComponent {
    protected List<TextComponent> children = new ArrayList<>();
    protected TextComponentType type;

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }

    @Override
    public List<TextComponent> getChildren() {
        return children;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }
}
