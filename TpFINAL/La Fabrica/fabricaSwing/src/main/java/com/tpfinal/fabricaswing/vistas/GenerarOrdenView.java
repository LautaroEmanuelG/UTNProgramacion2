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

        for (String orden : ordenesNoCumplidas) {
            modelOrdenes.addElement(orden);
            modelCumplidas.addElement("No Cumplida");
        }

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
            List<String> lines = Files.readAllLines(Paths.get("ordenes.txt"));
            List<String> newLines = new ArrayList<>();
            int ordenesActualizadas = 0;

            for (String line : lines) {
                line = line.trim();
                String[] parts = line.split("\\s*,\\s*");

                if (parts.length == 3) {
                    String nombreProducto = parts[0].trim();
                    int cantidad = Integer.parseInt(parts[1].trim());
                    boolean cumplida = Boolean.parseBoolean(parts[2].trim());

                    if (!cumplida) {
                        Producto producto = buscarProductoPorNombre(nombreProducto);
                        if (producto != null) {
                            List<MateriaPrima> materiasNecesarias = new ArrayList<>();
                            boolean puedeCumplirOrden = obtenerMateriasPrimasNecesarias(producto, cantidad, materiasNecesarias, new ArrayList<>(materiasPrimas));

                            if (puedeCumplirOrden) {
                                if (descontarMateriasPrimas(materiasNecesarias, cantidad, materiasPrimas)) {
                                    OrdenProduccion orden = new OrdenProduccion(nombreProducto, true, cantidad, producto);
                                    guardarOrdenProduccion(orden);
                                    ordenesActualizadas++;
                                    newLines.add(nombreProducto + "," + cantidad + ",true");
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

            Files.write(Paths.get("ordenes.txt"), newLines);
            JOptionPane.showMessageDialog(null, "Se actualizaron " + ordenesActualizadas + " órdenes pendientes.");
            actualizarListaOrdenes();
            cargarDatosDesdeArchivo(new File(datosStock)); // actualizar la lista de materias primas
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedIndex = jList1.getSelectedIndex();
        if (selectedIndex != -1) {
            String cantidadStr = jTextField2.getText().trim();
            if (!cantidadStr.isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    Producto producto = productos.get(selectedIndex);
                    if (producto != null) {
                        boolean puedeCumplirOrden = true;
                        for (Object obj : producto.getMateriasPrimas()) {
                            if (obj instanceof MateriaPrima) {
                                MateriaPrima materiaPrima = (MateriaPrima) obj;
                                int cantidadNecesaria = cantidad * 10;
                                if (materiaPrima.getExistencia() < cantidadNecesaria) {
                                    puedeCumplirOrden = false;
                                    break;
                                }
                            }
                        }

                        OrdenProduccion orden = new OrdenProduccion(producto.getNombre(), puedeCumplirOrden, cantidad, producto);
                        guardarOrdenProduccion(orden);

                        String estadoOrden = puedeCumplirOrden ? "Cumplida" : "No cumplida";
                        DefaultListModel<String> model = (DefaultListModel<String>) jList2.getModel();
                        model.addElement(producto.getNombre() + " - " + cantidad);

                        DefaultListModel<String> modelEstado = (DefaultListModel<String>) jList3.getModel();
                        modelEstado.addElement(estadoOrden);
                        jList2.setModel(model);
                        jList3.setModel(modelEstado);
                        jTextField2.setText("");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese una cantidad.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un producto de la lista.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private boolean descontarMateriasPrimas(List<MateriaPrima> materiasNecesarias, int cantidad, List<MateriaPrima> listaMateriasPrimas) {
        for (MateriaPrima materia : materiasNecesarias) {
            MateriaPrima materiaExistente = buscarMateriaPrimaPorNombre(materia.getNombre());
            if (materiaExistente != null) {
                int nuevaExistencia = materiaExistente.getExistencia() - cantidad;
                if (nuevaExistencia >= 0) {
                    materiaExistente.setExistencia(nuevaExistencia);
                } else {
                    return false;
                }
            }
        }
        guardarDatosMateriasPrimas(listaMateriasPrimas);
        return true;
    }

    private void guardarDatosMateriasPrimas(List<MateriaPrima> listaMateriasPrimas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosStock))) {
            for (MateriaPrima materiaPrima : listaMateriasPrimas) {
                bw.write(materiaPrima.getNombre() + "," + materiaPrima.getExistencia());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarOrdenProduccion(OrdenProduccion ordenProduccion) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ordenes.txt", true))) {
            bw.write(ordenProduccion.getProducto_a_fabricar() + "," + ordenProduccion.getCantidad() + "," + ordenProduccion.getCumplida());
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la orden de producción: " + e.getMessage());
        }
    }

    private boolean obtenerMateriasPrimasNecesarias(Producto producto, int cantidad, List<MateriaPrima> materiasNecesarias, List<MateriaPrima> listaMateriasPrimas) {
        boolean puedeCumplirOrden = true;

        for (Object obj : producto.getMateriasPrimas()) {
            if (obj instanceof MateriaPrima) {
                MateriaPrima materiaPrima = (MateriaPrima) obj;
                MateriaPrima materiaExistente = buscarMateriaPrimaPorNombre(materiaPrima.getNombre());
                if (materiaExistente != null) {
                    int cantidadNecesaria = cantidad * 10;
                    if (materiaExistente.getExistencia() >= cantidadNecesaria) {
                        materiasNecesarias.add(new MateriaPrima(materiaExistente.getNombre(), cantidadNecesaria));
                    } else {
                        puedeCumplirOrden = false;
                        break;
                    }
                } else {
                    puedeCumplirOrden = false;
                    break;
                }
            } else if (obj instanceof Producto) {
                Producto productoComponente = (Producto) obj;
                boolean puedeCumplirComponente = obtenerMateriasPrimasNecesarias(productoComponente, cantidad, materiasNecesarias, listaMateriasPrimas);
                if (!puedeCumplirComponente) {
                    puedeCumplirOrden = false;
                    break;
                }
            }
        }

        return puedeCumplirOrden;
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
