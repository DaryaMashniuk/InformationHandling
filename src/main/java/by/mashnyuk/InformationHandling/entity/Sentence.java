package by.mashnyuk.InformationHandling.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence implements TextComponent {
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

    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<>();
        for (TextComponent component : components) {
            if (component instanceof Word) {
                words.add((Word) component);
            } else if (component instanceof Lexeme) {
                component.getChildren().stream()
                        .filter(c -> c instanceof Word)
                        .forEach(c -> words.add((Word) c));
            }
        }
        return words;
    }
}