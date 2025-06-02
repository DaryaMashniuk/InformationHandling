package by.mashnyuk.informationHandling.entity;

public class Lexeme extends TextComponent {
    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent component : children) {
            sb.append(component.getText());
        }
        return sb.toString();
    }
}
