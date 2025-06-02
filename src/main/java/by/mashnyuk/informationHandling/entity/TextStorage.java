package by.mashnyuk.informationHandling.entity;

import java.util.ArrayList;
import java.util.List;

public class TextStorage {
    private final List<TextComponent> components = new ArrayList<>();

    public void add(TextComponent component) {
        components.add(component);
    }

    public TextComponent get(int index) {
        return components.get(index);
    }

    public List<TextComponent> getAll() {
        return new ArrayList<>(components);
    }

    public void clear() {
        components.clear();
    }

    public int size() {
        return components.size();
    }
}
