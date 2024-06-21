/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpfinal.fabricaswing.entidades;

/**
 *
 * @author Lautaro
 */
public class MateriaPrima {
    private String nombre;
    private Integer existencia;

    public MateriaPrima(String nombre, Integer existencia) {
        this.nombre = nombre;
        this.existencia = existencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }
    
    
}
