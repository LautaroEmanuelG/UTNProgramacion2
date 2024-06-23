import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JButton button1;
    private JButton button2;
    private JPanel content;
    private JLabel encabezado;

    public Dashboard() {
        setLayout(new BorderLayout());
        setSize(500, 500);

        //Crear Encabezado
        encabezado = new JLabel("La Fabrica");
        JPanel encabezadoPanel = new JPanel();
        encabezadoPanel.add(encabezado);

        // Crear botones
        button1 = new JButton("Pagina1");
        button2 = new JButton("Pagina2");

        // Crear panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Crear panel de contenido
        content = new JPanel();
        content.setLayout(new CardLayout());

        // Crear Page1 y Page2 y añadirlos al panel de contenido
        Page1 page1 = new Page1();
        content.add(page1, "Page1");
        Page2 page2 = new Page2();
        content.add(page2, "Page2");

        // Añadir ActionListeners a los botones
        button1.addActionListener(e -> {
            CardLayout cl = (CardLayout) content.getLayout();
            cl.show(content, "Page1");
        });
        button2.addActionListener(e -> {
            CardLayout cl = (CardLayout) content.getLayout();
            cl.show(content, "Page2");
        });

        // Crear panel del norte que contiene el encabezado y los botones
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(encabezadoPanel, BorderLayout.NORTH);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Añadir los paneles al JFrame
        add(northPanel, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

        // Configurar JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}