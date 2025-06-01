package by.mashnyuk.InformationHandling.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text implements TextComponent {
    private final List<TextComponent> components = new ArrayList<>();

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        components.forEach(c -> sb.append(c.getText()));
        return sb.toString();
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(components);
    }
}