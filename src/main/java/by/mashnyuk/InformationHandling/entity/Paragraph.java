package by.mashnyuk.InformationHandling.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Paragraph implements TextComponent {
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

    public List<Sentence> getAllSentences() {
        return components.stream()
                .filter(c -> c instanceof Sentence)
                .map(c -> (Sentence) c)
                .collect(Collectors.toList());
    }
}
