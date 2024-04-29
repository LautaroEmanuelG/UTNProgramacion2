package tpn6.tpn6.entities;

import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Profesor extends Persona{
    public ArrayList<Asignatura> cursos = new ArrayList<>();

    public String mostrarProfesor() {
        return "Profesor: " + this.nombre + " Legajo: " + this.legajo + " Cursos: " + this.cursos.size();
    }

    @Override
    public String toString() {
        return "Profesor: " + this.nombre + " Cursos: " + this.cursos.stream().map(Asignatura::getCodigo).collect(Collectors.toList());
    }
}
