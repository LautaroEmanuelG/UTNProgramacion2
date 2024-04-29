package tpn6.tpn6.entities;

import lombok.Data;

@Data
public class Inscripcion {
    private java.util.Date fecha;
    public Asignatura en;
    public Estudiante inscripto;

    public String mostrarInscripcion() {
        return "Inscripcion: " + this.fecha + " en " + this.en + " por " + this.inscripto;
    }

    @Override
    public String toString() {
    return "Inscripcion: " + this.fecha + " Estudiante: " + (this.inscripto != null ? this.inscripto.getNombre() : "Ninguno");
    }
}
