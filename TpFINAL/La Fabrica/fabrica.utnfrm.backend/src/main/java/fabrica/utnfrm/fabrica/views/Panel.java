import javax.swing.JButton;
import javax.swing.JLabel;

public class Panel {
    private JButton addButton;
    private JLabel stockLabel;
    private JLabel orderLabel;

    public Panel() {
        addButton = new JButton("Agregar Producto");
        stockLabel = new JLabel("Stock y Productos: ");
        orderLabel = new JLabel("Pedido y Estado: ");

        // Add action listener to the button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to handle adding a product
            }
        });

        // Add components to the panel
        add(addButton);
        add(stockLabel);
        add(orderLabel);
    }
}
