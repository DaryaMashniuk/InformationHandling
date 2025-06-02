package by.mashnyuk.informationHandling.entity;

public class Sentence extends TextComponent {
    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent lexeme : children) {
            sb.append(lexeme.getText()).append(" ");
        }
        return sb.toString().trim();
    }
}
