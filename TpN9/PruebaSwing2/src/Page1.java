import javax.swing.*;
import java.awt.*;

public class Page1 extends JPanel {
    private JLabel welcomeLabel;

    public Page1() {
        welcomeLabel = new JLabel("Bienvenidos a la fábrica");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel);
    }
}