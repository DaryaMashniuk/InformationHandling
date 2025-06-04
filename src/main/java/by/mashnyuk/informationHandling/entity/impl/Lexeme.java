package by.mashnyuk.informationHandling.entity.impl;

import by.mashnyuk.informationHandling.entity.AbstractTextComponent;
import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.TextComponentType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lexeme extends AbstractTextComponent {
    public Lexeme() {
        this.type = TextComponentType.LEXEME;
    }

    @Override
    public String getText() {
        return children.stream()
                .map(TextComponent::getText)
                .collect(Collectors.joining());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lexeme lexeme = (Lexeme) o;
        return Objects.equals(children, lexeme.children) &&
                type == lexeme.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (children != null ? children.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lexeme{");
        sb.append("children=").append(children);
        sb.append('}');
        return sb.toString();
    }
}