/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.tpfinal.fabricaswing.vistas;

import com.tpfinal.fabricaswing.entidades.MateriaPrima;
import com.tpfinal.fabricaswing.entidades.Producto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Lautaro
 */
public class AgregarProductoView extends javax.swing.JPanel {

    private Set<MateriaPrima> materiasPrimas;
    private Set<Producto> productos;
    private DefaultListModel<String> modelMateriasPrimas;
    private DefaultListModel<String> modelProductos;
    private final String datosStock = "materias_primas.txt";
    private final String productosStock = "productos.txt";
    private List<String> recetaItems;

    /**
     * Creates new form AgregarProductoView
     */
    public AgregarProductoView() {
        initComponents();
        this.setSize(335, 850);
        materiasPrimas = new HashSet<>();
        productos = new HashSet<>();
        modelMateriasPrimas = new DefaultListModel<>();
        modelProductos = new DefaultListModel<>();
        listMat.setModel(modelMateriasPrimas);
        listProductos.setModel(modelProductos);
        recetaItems = new ArrayList<>();
        cargarDatosDesdeArchivo(new File(datosStock), materiasPrimas);
        cargarDatosDesdeArchivo(new File(productosStock), productos);
        actualizarListas();
    }

    private void cargarDatosDesdeArchivo(File file, Set set) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al crear el archivo: " + e.getMessage());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    String nombre = partes[0].trim();
                    if (set == materiasPrimas) {
                        int existencia = Integer.parseInt(partes[1].trim());
                        MateriaPrima materia = new MateriaPrima(nombre, existencia);
                        materiasPrimas.add(materia);
                    } else if (set == productos) {
                        List<Object> materiasPrimasList = new ArrayList<>();
                        for (int i = 1; i < partes.length; i++) {
                            String nombreMateriaPrima = partes[i].trim();
                            boolean encontrado = false;
                            // Buscar si es una materia prima o un producto existente
                            for (MateriaPrima materiaPrima : materiasPrimas) {
                                if (materiaPrima.getNombre().equals(nombreMateriaPrima)) {
                                    materiasPrimasList.add(materiaPrima);
                                    encontrado = true;
                                    break;
                                }
                            }
                            if (!encontrado) {
                                // Puede ser un nombre de otro producto
                                for (Producto producto : productos) {
                                    if (producto.getNombre().equals(nombreMateriaPrima)) {
                                        materiasPrimasList.add(producto);
                                        break;
                                    }
                                }
                            }
                        }
                        Producto producto = new Producto(nombre, materiasPrimasList);
                        productos.add(producto);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos desde el archivo: " + e.getMessage());
        }
    }

    private void guardarDatosEnArchivo(File file, Set set) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Object item : set) {
                if (item instanceof MateriaPrima) {
                    MateriaPrima materia = (MateriaPrima) item;
                    bw.write(materia.getNombre() + "," + materia.getExistencia());
                } else if (item instanceof Producto) {
                    Producto producto = (Producto) item;
                    bw.write(producto.getNombre() + ",");
                    List<Object> materiasPrimas = producto.getMateriasPrimas();
                    for (int i = 0; i < materiasPrimas.size(); i++) {
                        if (materiasPrimas.get(i) instanceof MateriaPrima) {
                            MateriaPrima materiaPrima = (MateriaPrima) materiasPrimas.get(i);
                            bw.write(materiaPrima.getNombre());
                        } else if (materiasPrimas.get(i) instanceof Producto) {
                            Producto subproducto = (Producto) materiasPrimas.get(i);
                            bw.write(subproducto.getNombre());
                        }
                        if (i < materiasPrimas.size() - 1) {
                            bw.write(",");
                        }
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar datos en el archivo: " + e.getMessage());
        }
    }

    private void actualizarListas() {
        modelMateriasPrimas.clear();
        modelProductos.clear();

        for (MateriaPrima materia : materiasPrimas) {
            modelMateriasPrimas.addElement(materia.getNombre());
        }

        for (Producto producto : productos) {
            modelProductos.addElement(producto.getNombre());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        crearProducto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMat = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listProductos = new javax.swing.JList<>();
        addMat = new javax.swing.JButton();
        addProducto = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        Receta = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setPreferredSize(new java.awt.Dimension(300, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar Producto");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Materias Primas");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Productos");

        crearProducto.setText("Agregar Producto");
        crearProducto.setPreferredSize(new java.awt.Dimension(150, 30));
        crearProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProductoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Nombre");

        listMat.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listMat);

        listProductos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listProductos);

        addMat.setText("Añadir");
        addMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMatActionPerformed(evt);
            }
        });

        addProducto.setText("Añadir");
        addProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Receta");

        Receta.setMaximumSize(new java.awt.Dimension(9999, 9990));
        Receta.setPreferredSize(new java.awt.Dimension(280, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(crearProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addProducto))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addMat)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Receta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(addMat)
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProducto)
                    .addComponent(crearProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(Receta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(166, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void crearProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProductoActionPerformed
        String nombreProducto = jTextField1.getText().trim();

        if (nombreProducto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre del producto.");
            return;
        }

        if (existeProducto(nombreProducto)) {
            JOptionPane.showMessageDialog(this, "Ya existe un producto con ese nombre.");
            return;
        }

        if (recetaItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione al menos una materia prima para el producto.");
            return;
        }

        // Crear un nuevo producto con el nombre ingresado y las materias primas seleccionadas
        List<Object> materiasArray = new ArrayList<>();
        for (String nombreItem : recetaItems) {
            // Buscar si es una materia prima o un producto existente
            boolean encontrado = false;
            for (MateriaPrima materia : materiasPrimas) {
                if (materia.getNombre().equals(nombreItem)) {
                    materiasArray.add(materia);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                for (Producto producto : productos) {
                    if (producto.getNombre().equals(nombreItem)) {
                        materiasArray.add(producto);
                        break;
                    }
                }
            }
        }

        // Crear el nuevo producto con la lista de materias primas seleccionadas
        Producto nuevoProducto = new Producto(nombreProducto, materiasArray);

        // Agregar el nuevo producto a la lista de productos
        productos.add(nuevoProducto);

        // Actualizar las listas visuales
        actualizarListas();

        // Guardar los datos actualizados en el archivo productos.txt
        guardarDatosEnArchivo(new File(productosStock), productos);

        // Limpiar el nombre del producto y las materias seleccionadas para el siguiente producto
        jTextField1.setText("Nuevo Producto");
        recetaItems.clear(); // Limpiar los elementos de la receta
        updateRecetaLabel();
    }//GEN-LAST:event_crearProductoActionPerformed

    private void addMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMatActionPerformed
        int index = listMat.getSelectedIndex();
        if (index != -1) {
            MateriaPrima materiaSeleccionada = (MateriaPrima) materiasPrimas.toArray()[index];
            if (!recetaItems.contains(materiaSeleccionada.getNombre())) {
                recetaItems.add(materiaSeleccionada.getNombre());
                updateRecetaLabel();
            }
        }
    }//GEN-LAST:event_addMatActionPerformed

    private void addProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductoActionPerformed
        int index = listProductos.getSelectedIndex();
        if (index != -1) {
            Producto productoSeleccionado = (Producto) productos.toArray()[index];
            if (!recetaItems.contains(productoSeleccionado.getNombre())) {
                recetaItems.add(productoSeleccionado.getNombre());
                updateRecetaLabel();
            }
        }
    }//GEN-LAST:event_addProductoActionPerformed

    private void updateRecetaLabel() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recetaItems.size(); i++) {
            sb.append(recetaItems.get(i));
            if (i < recetaItems.size() - 1) {
                sb.append(" - ");
            }
        }
        Receta.setText(sb.toString());
    }

    private boolean existeProducto(String nombreProducto) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                return true;
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Receta;
    private javax.swing.JButton addMat;
    private javax.swing.JButton addProducto;
    private javax.swing.JButton crearProducto;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> listMat;
    private javax.swing.JList<String> listProductos;
    // End of variables declaration//GEN-END:variables
}
