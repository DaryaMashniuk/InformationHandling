package by.mashnyuk.informationHandling.entity;

public class Paragraph extends TextComponent {
    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent sentence : children) {
            sb.append(sentence.getText()).append(" ");
        }
        return sb.toString().trim();
    }
}
