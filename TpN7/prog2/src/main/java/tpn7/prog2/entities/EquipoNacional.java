package tpn7.prog2.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EquipoNacional {
    private String pais;
    private String color;
    
    private List<Atleta> pertenece;

    public EquipoNacional(String pais, String color, ArrayList<Atleta> atletas) {
        this.pais = pais;
        this.color = color;
        this.pertenece = new ArrayList<>();
    }
}
