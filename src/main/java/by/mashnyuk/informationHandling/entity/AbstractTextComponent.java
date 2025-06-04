package by.mashnyuk.informationHandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void setType(TextComponentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTextComponent that = (AbstractTextComponent) o;
        return children == that.children && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = 1;

        for (TextComponent child : children) {
            result = 31 * result + (child != null ? child.hashCode() : 0);
        }

        result = 31 * result + (type != null ? type.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "AbstractTextComponent{" +
                "children=" + children +
                ", type=" + type +
                '}';
    }
}