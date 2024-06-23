/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.tpfinal.fabricaswing.vistas;

import com.tpfinal.fabricaswing.entidades.MateriaPrima;
import com.tpfinal.fabricaswing.entidades.OrdenProduccion;
import com.tpfinal.fabricaswing.entidades.Producto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lautaro
 */
public class GenerarOrdenView extends javax.swing.JPanel {

    private List<Producto> productos;
    private List<MateriaPrima> materiasPrimas;
    private final String datosStock = "materias_primas.txt";
    private final String datosProductos = "productos.txt";

    /**
     * Creates new form GenerarOrdenView
     */
    public GenerarOrdenView() {
        initComponents();
        productos = new ArrayList<>();
        materiasPrimas = new ArrayList<>();

        cargarDatosDesdeArchivo(new File(datosStock));
        cargarProductosDesdeArchivo(new File(datosProductos));
        actualizarListaProductos();
        actualizarListaOrdenes();
    }

    private void actualizarListaOrdenes() {
        DefaultListModel<String> modelOrdenes = new DefaultListModel<>();
        DefaultListModel<String> modelCumplidas = new DefaultListModel<>();

        // Listas temporales para almacenar las órdenes no cumplidas y cumplidas
        List<String> ordenesNoCumplidas = new ArrayList<>();
        List<String> ordenesCumplidas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("ordenes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nombreProducto = parts[0];
                    String cantidad = parts[1];
                    String cumplida = parts[2];

                    String orden = nombreProducto + " - " + cantidad;
                    if (cumplida.equals("true")) {
                        ordenesCumplidas.add(orden);
                    } else {
                        ordenesNoCumplidas.add(orden);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Agregar primero las órdenes no cumplidas
        for (String orden : ordenesNoCumplidas) {
            modelOrdenes.addElement(orden);
            modelCumplidas.addElement("No Cumplida");
        }

        // Agregar las órdenes cumplidas
        for (String orden : ordenesCumplidas) {
            modelOrdenes.addElement(orden);
            modelCumplidas.addElement("Cumplida");
        }

        jList2.setModel(modelOrdenes);
        jList3.setModel(modelCumplidas);
    }

    private void actualizarListaProductos() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Producto producto : productos) {
            model.addElement(producto.getNombre());
        }
        jList1.setModel(model);
    }

    private void cargarDatosDesdeArchivo(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al crear el archivo: " + e.getMessage());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(datosStock))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    int existencia = Integer.parseInt(partes[1].trim());
                    MateriaPrima materia = new MateriaPrima(nombre, existencia);
                    materiasPrimas.add(materia);
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos desde el archivo: " + e.getMessage());
        }
    }

    private void cargarProductosDesdeArchivo(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al crear el archivo: " + e.getMessage());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(datosProductos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    String nombre = partes[0].trim();
                    List<Object> materiasPrimasProducto = new ArrayList<>();
                    for (int i = 1; i < partes.length; i++) {
                        String materia = partes[i].trim();
                        Producto productoExistente = buscarProductoPorNombre(materia);
                        if (productoExistente != null) {
                            materiasPrimasProducto.add(productoExistente);
                        } else {
                            MateriaPrima materiaPrimaExistente = buscarMateriaPrimaPorNombre(materia);
                            if (materiaPrimaExistente != null) {
                                materiasPrimasProducto.add(materiaPrimaExistente);
                            } else {
                                // Si no es una materia prima existente, puede ser un producto nuevo
                                // Creamos un producto temporal y lo agregamos a la lista
                                Producto nuevoProducto = new Producto(materia, new ArrayList<>());
                                System.out.println("Cargando producto" + materia);
                                materiasPrimasProducto.add(nuevoProducto);
                            }
                        }
                    }
                    Producto producto = new Producto(nombre, materiasPrimasProducto);
                    productos.add(producto);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos desde el archivo: " + e.getMessage());
        }
    }

    private Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    private MateriaPrima buscarMateriaPrimaPorNombre(String nombre) {
        for (MateriaPrima materiaPrima : materiasPrimas) {
            if (materiaPrima.getNombre().equalsIgnoreCase(nombre)) {
                return materiaPrima;
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();

        setMaximumSize(new java.awt.Dimension(300, 500));
        setPreferredSize(new java.awt.Dimension(300, 500));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Listado de Productos");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Cantidad :");

        jButton1.setText("Generar Orden");
        jButton1.setPreferredSize(new java.awt.Dimension(150, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Historial de ordenes");

        jButton2.setText("Procesar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addGap(60, 91, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 78, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Procesar ordenes pendientes
        procesarOrdenesPendientes();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void procesarOrdenesPendientes() {
        try {
            // Leer todas las líneas del archivo "ordenes.txt"
            List<String> lines = Files.readAllLines(Paths.get("ordenes.txt"));
            List<String> newLines = new ArrayList<>();
            List<String> ordenesCumplidas = new ArrayList<>();
            int ordenesActualizadas = 0;

            // Recorrer cada línea del archivo
            for (String line : lines) {
                line = line.trim(); // Eliminar espacios en blanco al inicio y al final
                String[] parts = line.split("\\s*,\\s*");

                if (parts.length == 3) {
                    String nombreProducto = parts[0].trim();
                    int cantidad = Integer.parseInt(parts[1].trim());
                    boolean cumplida = Boolean.parseBoolean(parts[2].trim());

                    if (!cumplida) { // Si la orden no está cumplida
                        Producto producto = buscarProductoPorNombre(nombreProducto);
                        if (producto != null) {
                            // Crear una copia de la lista original de materias primas
                            List<MateriaPrima> materiasPrimasCopia = new ArrayList<>();
                            for (MateriaPrima materiaPrima : materiasPrimas) {
                                MateriaPrima copia = new MateriaPrima(materiaPrima.getNombre(), materiaPrima.getExistencia());
                                materiasPrimasCopia.add(copia);
                            }

                            // Generar orden y evaluar la posibilidad de fabricación
                            List<MateriaPrima> materiasNecesarias = new ArrayList<>();
                            if (obtenerMateriasPrimasNecesarias(producto, cantidad, materiasNecesarias, materiasPrimasCopia)) {
                                // Si se puede fabricar, descontar las materias primas
                                if (descontarMateriasPrimas(materiasNecesarias, cantidad, materiasPrimas)) {
                                    OrdenProduccion orden = new OrdenProduccion(
                                            producto.getNombre(),
                                            true,
                                            cantidad,
                                            producto
                                    );
                                    ordenesCumplidas.add(nombreProducto + " - " + cantidad);
                                    newLines.add(nombreProducto + "," + cantidad + ",true");
                                    ordenesActualizadas++;
                                } else {
                                    newLines.add(nombreProducto + "," + cantidad + ",false");
                                }
                            } else {
                                newLines.add(nombreProducto + "," + cantidad + ",false");
                            }
                        } else {
                            newLines.add(line);
                        }
                    } else {
                        newLines.add(line);
                    }
                } else {
                    newLines.add(line);
                }
            }

            // Escribir las nuevas líneas actualizadas al archivo "ordenes.txt"
            Files.write(Paths.get("ordenes.txt"), newLines);

            // Mostrar un mensaje con las órdenes cumplidas
            if (!ordenesCumplidas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Se actualizaron las siguientes órdenes pendientes:\n" + String.join("\n", ordenesCumplidas));
            } else {
                JOptionPane.showMessageDialog(null, "No se actualizaron órdenes pendientes.");
            }

            // Actualizar la lista de órdenes después de procesar las órdenes pendientes
            actualizarListaOrdenes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean obtenerMateriasPrimasNecesarias(Producto producto, int cantidad, List<MateriaPrima> materiasNecesarias, List<MateriaPrima> materiasPrimasCopia) {
        // Recorrer las materias primas necesarias para el producto y cantidad
        for (Object materia : producto.getMateriasPrimas()) {
            if (materia instanceof MateriaPrima) {
                // Si es una materia prima, buscarla en la copia y agregarla a la lista de necesarias
                MateriaPrima materiaPrima = (MateriaPrima) materia;
                boolean found = false;
                for (MateriaPrima copia : materiasPrimasCopia) {
                    if (copia.getNombre().equalsIgnoreCase(materiaPrima.getNombre())) {
                        if (copia.getExistencia() >= cantidad) {
                            materiasNecesarias.add(new MateriaPrima(copia.getNombre(), cantidad));
                            copia.setExistencia(copia.getExistencia() - cantidad); // Reducir existencias en la copia
                            found = true;
                        }
                        break;
                    }
                }
                if (!found) {
                    return false; // No se encontró suficiente materia prima en la copia
                }
            } else if (materia instanceof Producto) {
                // Buscar el producto correspondiente en la lista de productos
                Producto subProducto = null;
                for (Producto p : productos) {
                    if (p.getNombre().equalsIgnoreCase(((Producto) materia).getNombre())) {
                        subProducto = p;
                        break;
                    }
                }
                // Si es un producto, llamar recursivamente para obtener sus materias primas necesarias
                if (!obtenerMateriasPrimasNecesarias(subProducto, cantidad, materiasNecesarias, materiasPrimasCopia)) {
                    return false; // No se pudieron obtener las materias primas necesarias para el subproducto
                }
            }
        }
        return true; // Se obtuvieron todas las materias primas necesarias
    }

    private boolean descontarMateriasPrimas(List<MateriaPrima> materiasNecesarias, int cantidad, List<MateriaPrima> materiasPrimas) {
        boolean inventarioSuficiente = true;

        for (MateriaPrima necesaria : materiasNecesarias) {
            String nombre = necesaria.getNombre();
            int cantidadNecesaria = necesaria.getExistencia();

            for (MateriaPrima existente : materiasPrimas) {
                if (existente.getNombre().equalsIgnoreCase(nombre)) {
                    if (existente.getExistencia() >= cantidadNecesaria) {
                        existente.setExistencia(existente.getExistencia() - cantidadNecesaria);
                    } else {
                        inventarioSuficiente = false;
                        break;
                    }
                }
            }

            if (!inventarioSuficiente) {
                break;
            }
        }

        if (inventarioSuficiente) {
            // Actualizar el archivo materias_primas.txt con las nuevas existencias
            guardarDatosMateriasPrimas();
        }

        return inventarioSuficiente;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedIndex = jList1.getSelectedIndex();
        if (selectedIndex != -1) {
            Producto productoSeleccionado = productos.get(selectedIndex);

            try {
                int cantidad = Integer.parseInt(jTextField2.getText());

                // Crear una copia de la lista original de materias primas
                List<MateriaPrima> materiasPrimasCopia = new ArrayList<>();
                for (MateriaPrima materiaPrima : materiasPrimas) {
                    MateriaPrima copia = new MateriaPrima(materiaPrima.getNombre(), materiaPrima.getExistencia());
                    materiasPrimasCopia.add(copia);
                }

                // Generar orden y evaluar la posibilidad de fabricación
                List<MateriaPrima> materiasNecesarias = new ArrayList<>();
                if (obtenerMateriasPrimasNecesarias(productoSeleccionado, cantidad, materiasNecesarias, materiasPrimasCopia)) {
                    // Si se puede fabricar, descontar las materias primas
                    if (descontarMateriasPrimas(materiasNecesarias, cantidad, materiasPrimas)) {
                        OrdenProduccion orden = new OrdenProduccion(
                                productoSeleccionado.getNombre(),
                                true,
                                cantidad,
                                productoSeleccionado
                        );
                        guardarOrdenProduccion(orden);
                        JOptionPane.showMessageDialog(this, "Orden generada y cumplida exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "No hay suficiente inventario para generar la orden.");
                    }
                } else {
                    OrdenProduccion orden = new OrdenProduccion(
                            productoSeleccionado.getNombre(),
                            false,
                            cantidad,
                            productoSeleccionado
                    );
                    guardarOrdenProduccion(orden);
                    JOptionPane.showMessageDialog(this, "No hay suficiente inventario para generar la orden.");
                }

                // Actualizar la lista de órdenes después de guardar la orden
                actualizarListaOrdenes();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void guardarDatosMateriasPrimas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosStock))) {
            for (MateriaPrima materia : materiasPrimas) {
                bw.write(materia.getNombre() + "," + materia.getExistencia());
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos de materias primas: " + e.getMessage());
        }
    }

    private void guardarOrdenProduccion(OrdenProduccion orden) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ordenes.txt", true))) {
            bw.write(orden.getProducto_a_fabricar() + "," + orden.getCantidad() + "," + orden.getCumplida());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
