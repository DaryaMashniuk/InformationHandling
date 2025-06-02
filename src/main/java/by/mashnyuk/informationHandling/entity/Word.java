package by.mashnyuk.informationHandling.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word extends TextComponent {
    private final String value;

    public Word(String value) {
        this.value = value;
    }

    @Override
    public String getText() {
        return value;
    }
}