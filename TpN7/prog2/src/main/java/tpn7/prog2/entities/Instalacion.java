package tpn7.prog2.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instalacion {
    private String nombre;
    private String tipo;
    private String categoria;
    private String localizacion;
    private List<Prueba> seHace;
    private List<Sede> sede;

    public Instalacion(String nombre, String tipo, String categoria, String localizacion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.localizacion = localizacion;
        this.seHace = new ArrayList<>();
        this.sede = new ArrayList<>();
    }
}
