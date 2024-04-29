package tpn6.tpn6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tpn6.tpn6.entities.Asignatura;
import tpn6.tpn6.entities.Estudiante;
import tpn6.tpn6.entities.Inscripcion;
import tpn6.tpn6.entities.Profesor;

@SpringBootApplication
public class Tpn6Application {

	public static void main(String[] args) {
		SpringApplication.run(Tpn6Application.class, args);

		Profesor profesor = new Profesor();
		profesor.setNombre("Juan");
		profesor.setLegajo(1234);

		Asignatura asignatura = new Asignatura();
		asignatura.setCodigo(1);
		asignatura.setInstructor(profesor);
		Asignatura asignatura2 = new Asignatura();
		asignatura2.setCodigo(2);
		asignatura2.setInstructor(profesor);

		profesor.getCursos().add(asignatura);
		profesor.getCursos().add(asignatura2);

		Estudiante estudiante = new Estudiante();
		estudiante.setNombre("Pedro");
		estudiante.setLegajo(4321);
		Estudiante estudiante2 = new Estudiante();
		estudiante2.setNombre("Maria");
		estudiante2.setLegajo(5678);
		Estudiante estudiante3 = new Estudiante();
		estudiante3.setNombre("Jose");
		estudiante3.setLegajo(8765);

		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setFecha(new java.util.Date());
		inscripcion.setEn(asignatura);
		inscripcion.setInscripto(estudiante);
		Inscripcion inscripcion2 = new Inscripcion();
		inscripcion2.setFecha(new java.util.Date());
		inscripcion2.setEn(asignatura);
		inscripcion2.setInscripto(estudiante2);
		Inscripcion inscripcion3 = new Inscripcion();
		inscripcion3.setFecha(new java.util.Date());
		inscripcion3.setEn(asignatura2);
		inscripcion3.setInscripto(estudiante3);

		estudiante.getInscripciones().put(1, inscripcion);
		estudiante.getInscripciones().put(2, inscripcion2);
		estudiante2.getInscripciones().put(1, inscripcion);
		estudiante2.getInscripciones().put(2, inscripcion2);
		estudiante3.getInscripciones().put(1, inscripcion3);

		System.out.println(profesor.mostrarProfesor());
		System.out.println(estudiante.mostrarEstudiante());
		System.out.println(estudiante2.mostrarEstudiante());
		System.out.println(estudiante3.mostrarEstudiante());
		System.out.println(asignatura.mostrarAsignatura());
		System.out.println(asignatura2.mostrarAsignatura());
		System.out.println(inscripcion.mostrarInscripcion());
		System.out.println(inscripcion2.mostrarInscripcion());
	}

}
