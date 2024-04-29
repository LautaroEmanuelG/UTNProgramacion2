package tpn7.prog2.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prueba {
    private String codigo;
    private String titulo;
    private List<Atleta> participa;
    private List<Instalacion> donde;

    public Prueba(String codigo, String titulo, ArrayList<Atleta> atletas, Instalacion instalacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.participa = new ArrayList<>();
        this.participa.addAll(atletas);
        this.donde = new ArrayList<>();
        this.donde.add(instalacion);
    }
}
