package tp5.tp5.entities;

import lombok.Data;

@Data
public class Viajero {
    private String nombre;
    private char sexo;
    private Equipaje[] guardaEquipaje;
}
