package tp5.tp5.entities;

import lombok.Data;

@Data
public class Chofer {
    private String nombre;
    public Taxi[] conduce;
    private int vencimiento = 2022;

    private int vencimientoLic( int fechaActual) {
        return vencimiento - fechaActual;
    }
}
