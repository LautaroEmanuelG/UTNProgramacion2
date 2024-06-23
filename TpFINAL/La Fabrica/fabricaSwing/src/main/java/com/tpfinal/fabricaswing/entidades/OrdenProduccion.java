/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpfinal.fabricaswing.entidades;

/**
 *
 * @author Lautaro
 */
public class OrdenProduccion {
    private String Producto_a_fabricar;
    private Boolean cumplida;
    private Integer cantidad;
    private Producto producto;

    public OrdenProduccion(String Producto_a_fabricar, Boolean cumplida, Integer cantidad, Producto producto) {
        this.Producto_a_fabricar = Producto_a_fabricar;
        this.cumplida = cumplida;
        this.cantidad = cantidad;
        this.producto = producto;
    }
    
    public String getProducto_a_fabricar() {
        return Producto_a_fabricar;
    }

    public void setProducto_a_fabricar(String Producto_a_fabricar) {
        this.Producto_a_fabricar = Producto_a_fabricar;
    }

    public Boolean getCumplida() {
        return cumplida;
    }

    public void setCumplida(Boolean cumplida) {
        this.cumplida = cumplida;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
