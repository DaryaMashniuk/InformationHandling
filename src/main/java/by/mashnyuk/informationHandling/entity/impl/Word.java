package by.mashnyuk.informationHandling.entity.impl;

import by.mashnyuk.informationHandling.entity.AbstractTextComponent;
import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.TextComponentType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Word extends AbstractTextComponent {
    private String word;

    public Word(String word) {
        this.word = word;
        this.type = TextComponentType.WORD;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String getText() {
        return word;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word) &&
                type == word1.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Word{");
        sb.append("word='").append(word).append('\'');
        sb.append('}');
        return sb.toString();
    }
}