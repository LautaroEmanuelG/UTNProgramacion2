import javax.swing.*;
import java.awt.*;

public class Page2 extends JPanel {
    private JLabel greetingLabel;

    public Page2() {
        greetingLabel = new JLabel("Saludos");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(greetingLabel);
    }
}