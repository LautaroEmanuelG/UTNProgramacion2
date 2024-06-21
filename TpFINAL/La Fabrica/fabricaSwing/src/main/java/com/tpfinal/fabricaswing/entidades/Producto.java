/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpfinal.fabricaswing.entidades;
import java.util.Arrays;
import java.util.Comparator;
/**
 *
 * @author Lautaro
 */
public class Producto {
    public String name;
    private MateriaPrima mat_primas[];//Pueden haber Productos
    
    public void procesar_ordenes_pendientes(){
        //Revisar si ahora podemos hacer
    }
    
     public int contarMateriaPrima(MateriaPrima mat) {
        return (int) Arrays.stream(mat_primas)
                .filter(materia -> materia.getNombre().equals(mat.getNombre()))
                .count();
    }
     
    public Producto[] cantidad_productos(MateriaPrima mat,Producto[] todosLosProductos){
        //Seleccionar Una materia y revisar los productos que mas utilicen esa materia prima
         // Crear un array para almacenar la cantidad de uso de la materia prima
        int[] cantidades = new int[todosLosProductos.length];

        // Contar el uso de la materia prima en cada producto
        for (int i = 0; i < todosLosProductos.length; i++) {
            Producto producto = todosLosProductos[i];
            cantidades[i] = producto.contarMateriaPrima(mat);
        }

        // Ordenar los productos por la cantidad de uso de la materia prima en orden descendente
        Producto[] productosOrdenados = Arrays.copyOf(todosLosProductos, todosLosProductos.length);
        Integer[] indices = new Integer[productosOrdenados.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(cantidades[o2], cantidades[o1]);
            }
        });

        Producto[] resultado = new Producto[productosOrdenados.length];
        for (int i = 0; i < indices.length; i++) {
            resultado[i] = productosOrdenados[indices[i]];
        }

        return resultado;
    }

    public Producto(String name, MateriaPrima[] mat_primas) {
        this.name = name;
        this.mat_primas = mat_primas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MateriaPrima[] getMat_primas() {
        return mat_primas;
    }

    public void setMat_primas(MateriaPrima[] mat_primas) {
        this.mat_primas = mat_primas;
    }

    
}
