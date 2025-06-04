package by.mashnyuk.informationHandling.entity.impl;

import by.mashnyuk.informationHandling.entity.AbstractTextComponent;
import by.mashnyuk.informationHandling.entity.TextComponent;
import by.mashnyuk.informationHandling.entity.TextComponentType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Punctuation extends AbstractTextComponent {
    private String mark;

    public Punctuation(String mark) {
        this.mark = mark;
        this.type = TextComponentType.PUNCTUATION;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String getText() {
        return mark;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punctuation that = (Punctuation) o;
        return Objects.equals(mark, that.mark) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Punctuation{");
        sb.append("mark='").append(mark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}