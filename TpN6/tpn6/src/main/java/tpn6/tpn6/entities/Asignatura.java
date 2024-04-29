package tpn6.tpn6.entities;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Asignatura {
    private Integer codigo;
    private Profesor instructor;
    public ArrayList<Inscripcion> inscripciones = new ArrayList<>();

    public String mostrarAsignatura() {
        return "Asignatura: " + this.codigo + " Instructor: " + this.instructor;
    }

    @Override
public String toString() {
    return "Asignatura: " + this.codigo + " Instructor: " + (this.instructor != null ? this.instructor.getNombre() : "Ninguno");
}
}