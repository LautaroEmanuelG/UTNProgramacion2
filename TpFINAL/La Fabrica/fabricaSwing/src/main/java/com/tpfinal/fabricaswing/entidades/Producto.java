/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tpfinal.fabricaswing.entidades;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lautaro
 */
public class Producto {
    public String nombre;
    private List<Object> materiasPrimas; // Lista que puede contener MateriaPrima o Producto

    // Constructor
    public Producto(String nombre, List<Object> materiasPrimas) {
        this.nombre = nombre;
        this.materiasPrimas = materiasPrimas;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Object> getMateriasPrimas() {
        return materiasPrimas;
    }

    public void setMateriasPrimas(List<Object> materiasPrimas) {
        this.materiasPrimas = materiasPrimas;
    }
}

