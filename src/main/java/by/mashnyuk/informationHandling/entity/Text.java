package by.mashnyuk.informationHandling.entity;

public class Text extends TextComponent {
    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent paragraph : children) {
            sb.append(paragraph.getText()).append("\n\n");
        }
        return sb.toString().trim();
    }
}