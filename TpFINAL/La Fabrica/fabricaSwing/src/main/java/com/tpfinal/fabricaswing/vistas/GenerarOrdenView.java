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
    private List<MateriaPrima> inventarioMateriasPrimas;
    private Map<String, Producto> mapaProductos;

    /**
     * Creates new form GenerarOrdenView
     */
    public GenerarOrdenView() {
        initComponents();
        productos = new ArrayList<>();
        inventarioMateriasPrimas = new ArrayList<>();
        mapaProductos = new HashMap<>();
        cargarProductos();
        cargarInventarioMateriasPrimas();
        actualizarListaProductos();
        actualizarListaOrdenes();
    }

    private void cargarProductos() {
        try (BufferedReader br = new BufferedReader(new FileReader("productos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Producto producto = parseProducto(line);
                if (producto != null) {
                    productos.add(producto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Producto parseProducto(String line) {
        String[] parts = line.split(",");

        if (parts.length < 2) {
            return null;
        }

        String nombreProducto = parts[0];
        List<Object> materiasPrimas = new ArrayList<>();

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].trim();

            if (part.startsWith("\"") && part.endsWith("\"")) {
                part = part.substring(1, part.length() - 1); // Eliminar comillas de inicio y fin si están presentes
            }

            if (part.startsWith("MP:")) {
                String nombreMateria = part.substring(3);
                materiasPrimas.add(new MateriaPrima(nombreMateria, 1)); // Inicializar con existencia 1
            } else if (part.startsWith("PR:")) {
                String nombreSubProducto = part.substring(3);
                List<Object> subProductoMaterias = new ArrayList<>();
                // Aquí se debería buscar y cargar las materias primas del subproducto (si es necesario)
                materiasPrimas.add(new Producto(nombreSubProducto, subProductoMaterias));
            } else {
                // Si no comienza con "MP:" o "PR:", asumimos que es un nombre de materia prima adicional
                materiasPrimas.add(new MateriaPrima(part, 1)); // Inicializar con existencia 1
            }
        }
        return new Producto(nombreProducto, materiasPrimas);
    }

    private void cargarInventarioMateriasPrimas() {
        try (BufferedReader br = new BufferedReader(new FileReader("materias_primas.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nombreMateria = parts[0];
                    int existencia = Integer.parseInt(parts[1]);
                    inventarioMateriasPrimas.add(new MateriaPrima(nombreMateria, existencia));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            System.out.println("Procesando");
            List<String> lines = Files.readAllLines(Paths.get("ordenes.txt"));
            List<String> newLines = new ArrayList<>();
            int ordenesActualizadas = 0;

            for (String line : lines) {
                line = line.trim(); // Eliminar espacios en blanco al inicio y al final
                String[] parts = line.split("\\s*,\\s*");
                if (parts.length == 3) {
                    String nombreProducto = parts[0].trim();
                    int cantidad = Integer.parseInt(parts[1].trim());
                    boolean cumplida = Boolean.parseBoolean(parts[2].trim());

                    if (!cumplida) {
                        Producto producto = null;
                        // Buscar el producto en la lista de productos
                        for (Producto p : productos) {
                            if (p.getNombre().equals(nombreProducto)) {
                                producto = p;
                                break;
                            }
                        }

                        if (producto != null) {
                            List<MateriaPrima> materiasNecesarias = new ArrayList<>();
                            System.out.println("Procesando orden para: " + producto.getNombre());

                            // Verificar si se pueden obtener las materias primas necesarias para la cantidad total
                            boolean puedeCumplirOrden = true;
                            for (int i = 0; i < cantidad; i++) {
                                if (!obtenerMateriasPrimasNecesarias(producto, 1, materiasNecesarias)) {
                                    puedeCumplirOrden = false;
                                    break;
                                }
                            }

                            if (puedeCumplirOrden) {
                                // Descontar las materias primas del inventario para la cantidad total
                                if (descontarMateriasPrimas(materiasNecesarias, cantidad)) {
                                    System.out.println("Orden cumplida para: " + producto.getNombre());

                                    // Crear una nueva orden cumplida
                                    OrdenProduccion orden = new OrdenProduccion(
                                            nombreProducto,
                                            true,
                                            cantidad,
                                            producto
                                    );
                                    guardarOrdenProduccion(orden);
                                    ordenesActualizadas++;
                                    newLines.add(nombreProducto + "," + cantidad + ",true");
                                } else {
                                    System.out.println("Inventario insuficiente para: " + producto.getNombre());

                                    // No se pudieron descontar las materias primas, la orden sigue pendiente
                                    newLines.add(nombreProducto + "," + cantidad + ",false");
                                }
                            } else {
                                System.out.println("Materias primas insuficientes para: " + producto.getNombre());

                                // No se pudieron obtener las materias primas necesarias, la orden sigue pendiente
                                newLines.add(nombreProducto + "," + cantidad + ",false");
                            }
                        } else {
                            System.out.println("Producto no encontrado: " + nombreProducto);

                            // No se encontró el producto, la orden sigue pendiente
                            newLines.add(line); // Mantener la orden original
                        }
                    } else {
                        // La orden ya estaba cumplida, la mantenemos igual
                        newLines.add(line);
                    }
                } else {
                    // Línea no válida, mantenerla tal cual en el archivo
                    newLines.add(line);
                }
            }

            // Escribir las nuevas líneas actualizadas al archivo
            Files.write(Paths.get("ordenes.txt"), newLines);

            // Mostrar un mensaje con la cantidad de órdenes actualizadas
            JOptionPane.showMessageDialog(null, "Se actualizaron " + ordenesActualizadas + " órdenes pendientes.");

            // Actualizar la lista de órdenes después de procesar las órdenes pendientes
            actualizarListaOrdenes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedIndex = jList1.getSelectedIndex();
        if (selectedIndex != -1) {
            Producto productoSeleccionado = productos.get(selectedIndex);
            // Imprimir para verificar que las materias primas están correctamente cargadas
            System.out.println(productoSeleccionado.getNombre() + " - " + productoSeleccionado.getMateriasPrimas());

            try {
                int cantidad = Integer.parseInt(jTextField2.getText());

                List<MateriaPrima> materiasNecesarias = new ArrayList<>();
                if (obtenerMateriasPrimasNecesarias(productoSeleccionado, cantidad, materiasNecesarias)) {
                    OrdenProduccion orden;
                    if (descontarMateriasPrimas(materiasNecesarias, cantidad)) {
                        orden = new OrdenProduccion(
                                productoSeleccionado.getNombre(),
                                true,
                                cantidad,
                                productoSeleccionado
                        );
                        guardarOrdenProduccion(orden);
                        JOptionPane.showMessageDialog(this, "Orden generada y cumplida exitosamente.");
                    } else {
                        orden = new OrdenProduccion(
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
                } else {
                    OrdenProduccion orden = new OrdenProduccion(
                            productoSeleccionado.getNombre(),
                            false,
                            cantidad,
                            productoSeleccionado
                    );
                    guardarOrdenProduccion(orden);
                    JOptionPane.showMessageDialog(this, "No hay suficiente inventario para generar la orden.");
                    // Actualizar la lista de órdenes después de guardar la orden
                    actualizarListaOrdenes();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean verificarMateriasPrimasRecursivo(Producto producto, int cantidad, Map<String, Integer> requerimientos) {
        for (Object item : producto.getMateriasPrimas()) {
            if (item instanceof MateriaPrima) {
                MateriaPrima mp = (MateriaPrima) item;
                int cantidadRequerida = mp.getExistencia() * cantidad;
                int existenciaActual = obtenerExistenciaMateriaPrima(mp.getNombre());

                if (existenciaActual < cantidadRequerida) {
                    return false; // No hay suficiente existencia para cumplir la orden
                }

                requerimientos.put(mp.getNombre(), requerimientos.getOrDefault(mp.getNombre(), 0) + cantidadRequerida);
            } else if (item instanceof Producto) {
                Producto subProducto = mapaProductos.get(((Producto) item).getNombre());
                if (subProducto != null) {
                    if (!verificarMateriasPrimasRecursivo(subProducto, cantidad, requerimientos)) {
                        return false;
                    }
                }
            }
        }
        return true; // Se pueden satisfacer todos los requerimientos de materias primas
    }

    private boolean obtenerMateriasPrimasNecesarias(Producto producto, int cantidad, List<MateriaPrima> materiasNecesarias) {
        Map<String, Integer> requerimientos = new HashMap<>();
        if (verificarMateriasPrimasRecursivo(producto, cantidad, requerimientos)) {
            for (Map.Entry<String, Integer> entry : requerimientos.entrySet()) {
                MateriaPrima mp = buscarMateriaPrimaEnInventario(entry.getKey());
                if (mp != null) {
                    materiasNecesarias.add(new MateriaPrima(mp.getNombre(), entry.getValue()));
                } else {
                    return false; // No hay suficiente inventario para cumplir la orden
                }
            }
            return true; // Se pueden satisfacer todos los requerimientos de materias primas
        }
        return false; // No se cumplen los requisitos de materias primas
    }

    private boolean descontarMateriasPrimas(List<MateriaPrima> materiasNecesarias, int cantidad) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("materias_primas.txt"));
            List<String> newLines = new ArrayList<>();

            boolean todasMateriasDescontadas = true;

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nombreMateria = parts[0];
                    int existencia = Integer.parseInt(parts[1]);

                    // Buscar la materia prima en la lista de necesarias
                    boolean encontrada = false;
                    for (MateriaPrima mp : materiasNecesarias) {
                        if (mp.getNombre().equals(nombreMateria)) {
                            encontrada = true;
                            // Verificar si hay suficiente existencia
                            if (existencia < mp.getExistencia()) {
                                todasMateriasDescontadas = false; // No hay suficiente existencia
                                break;
                            }
                            // Actualizar existencia restando la cantidad necesaria
                            existencia -= mp.getExistencia();
                            break;
                        }
                    }

                    // Crear la nueva línea con la existencia actualizada
                    newLines.add(nombreMateria + "," + existencia);
                } else {
                    newLines.add(line); // Mantener las líneas que no corresponden a materias primas
                }
            }

            // Si no se pudieron descontar todas las materias primas necesarias, retornar false
            if (!todasMateriasDescontadas) {
                return false;
            }

            // Escribir las líneas actualizadas de vuelta al archivo
            Files.write(Paths.get("materias_primas.txt"), newLines);
            return true; // Todas las materias primas necesarias fueron descontadas correctamente
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error al leer o escribir el archivo
        }
    }

    private int obtenerExistenciaMateriaPrima(String nombre) {
        for (MateriaPrima mp : inventarioMateriasPrimas) {
            if (mp.getNombre().equals(nombre)) {
                return mp.getExistencia();
            }
        }
        return 0; // Materia prima no encontrada (esto debería manejarse según el diseño de tu aplicación)
    }

    private MateriaPrima buscarMateriaPrimaEnInventario(String nombre) {
        for (MateriaPrima mp : inventarioMateriasPrimas) {
            if (mp.getNombre().equals(nombre)) {
                return mp;
            }
        }
        return null;
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
