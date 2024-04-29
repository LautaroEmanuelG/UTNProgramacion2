package tpn6.tpn6.entities;

import lombok.Data;

@Data
public class Persona {
    protected String nombre;
    public Integer legajo;

    public Persona(String nombre, Integer legajo) {
        this.nombre = nombre;
        this.legajo = legajo;
    }

    public Persona() {}

    public String mostrarAtributos() {
        return "Nombre: " + this.nombre + " Legajo: " + this.legajo;
    }
}
