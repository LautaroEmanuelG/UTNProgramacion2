package tpn7.prog2.entities;

import lombok.Data;

@Data
public class Sede {
    private String fase;
    private String fecha;
    private String hora;
    private Instalacion sede;
    private String codigoPrueba;

    public Sede(String fase, String fecha, String hora, Instalacion sede) {
        this.fase = fase;
        this.fecha = fecha;
        this.hora = hora;
        this.sede = sede;
    }
}
