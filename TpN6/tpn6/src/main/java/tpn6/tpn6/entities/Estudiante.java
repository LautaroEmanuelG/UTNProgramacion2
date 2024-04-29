package tpn6.tpn6.entities;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Estudiante extends Persona{
    public HashMap<Integer,Inscripcion> inscripciones = new HashMap<>();

    public String mostrarEstudiante() {
        return "Estudiante: " + this.nombre + " Legajo: " + this.legajo + " Inscripciones: " + this.inscripciones.size();
    }

    @Override
    public String toString() {
    return "Estudiante: " + this.nombre + " Número de inscripciones: " + this.inscripciones.size();
    }
}
