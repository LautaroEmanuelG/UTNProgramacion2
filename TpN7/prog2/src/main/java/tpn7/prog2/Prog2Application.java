package tpn7.prog2;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tpn7.prog2.entities.Atleta;
import tpn7.prog2.entities.EquipoNacional;
import tpn7.prog2.entities.Instalacion;
import tpn7.prog2.entities.Prueba;
import tpn7.prog2.entities.Sede;

@SpringBootApplication
public class Prog2Application {

	public static void main(String[] args) {
		SpringApplication.run(Prog2Application.class, args);

		Atleta atleta1 = new Atleta("Juan", 4044444, 25, 1.80, 80);
		Atleta atleta2 = new Atleta("Pedro", 4044445, 30, 1.70, 70);
		Atleta atleta3 = new Atleta("Maria", 4044446, 35, 1.60, 60);
		Atleta atleta4 = new Atleta("Jose", 4044447, 40, 1.50, 50);
		Atleta atleta5 = new Atleta("Ana", 4044448, 45, 1.40, 90);

		ArrayList<Atleta> atletasPrueba1 = new ArrayList<>();
		atletasPrueba1.add(atleta1);
		atletasPrueba1.add(atleta2);
		ArrayList<Atleta> atletasPrueba2 = new ArrayList<>();
		atletasPrueba2.add(atleta3);
		atletasPrueba2.add(atleta4);
		atletasPrueba2.add(atleta5);
		
		Instalacion instalacion1 = new Instalacion("i1", "Running", "Estadio", "CABA");
		Prueba prueba1 = new Prueba("p1", "Maraton", atletasPrueba1, instalacion1);
		prueba1.getDonde().add(instalacion1);
		Sede sede1 = new Sede("Final", "10/10/2021", "10:00", instalacion1);
		sede1.setCodigoPrueba(prueba1.getCodigo());

		Instalacion instalacion2 = new Instalacion("i2", "Natacion", "Pileta", "CABA");
		Prueba prueba2 = new Prueba("p2", "100m Libre", atletasPrueba2, instalacion2);
		prueba2.getDonde().add(instalacion2);
		Sede sede2 = new Sede("Final", "10/10/2021", "12:00", instalacion2);
		sede2.setCodigoPrueba(prueba2.getCodigo());

		// Nombre del tercer atleta de la prueba 2
		System.out.println("Nombre del tercer atleta de la prueba 2, desde instalacion: " + instalacion2.getSeHace().get(0).getParticipa().get(2).getNombre());
		// Codigo de la 1 prueba
		System.out.println("Codigo de la 1 prueba, desde instalacion: " + instalacion1.getSeHace().get(0).getCodigo());

		ArrayList<Atleta> atletasEquipo = new ArrayList<>();
		atletasEquipo.addAll(atletasPrueba1);
		atletasEquipo.addAll(atletasPrueba2);
		EquipoNacional equipoNacional = new EquipoNacional("Argentina", "Celeste y Blanca", atletasEquipo);

		// Mostrar altura de todo los atletas desde equipo nacional
		for (Atleta atleta : atletasEquipo) {
			System.out.println("Atleta: " + atleta.getNombre() + " Altura: " + atleta.getAltura());
		}
		// Mostrar peso extra si lo hay, desde equipo nacional
		for (Atleta atleta : atletasEquipo) {
			float imc = atleta.calcularIMC();
			if (atleta.hayPesoExtra(imc)) {
				System.out.println("Atleta: " + atleta.getNombre() + " Peso Extra: "+ imc);
			}
		}

		// 6 a-Guardar en una variable el valor del código de la segunda prueba que se hará en una instalación.
		var codigoPrueba2 = prueba2.getCodigo();
		//b- Guardar en una variable, la categoría de la tercera instalación que se utilizará para una prueba.
		var categoriaInstalacion3 = instalacion2.getCategoria();
		//c- Mostrar el valor del IMC del primer atleta que participa de la primera prueba que se realiza en una instalación.
		System.out.println("IMC del primer atleta de la primera prueba: " + atletasPrueba1.get(0).calcularIMC());
		//d- Guardar en una variable, las pulsaciones tomadas a un atleta que realiza una prueba en una instalación del tipo indicado.
		var pulsacionesAtleta = atleta1.tomarPulsaciones();

		System.out.println("6-a: " + codigoPrueba2);
		System.out.println("6-b: " + categoriaInstalacion3);
		System.out.println("6-d: " + pulsacionesAtleta);
		System.out.println("Equipo Nacional: " + equipoNacional.getPais());
	}

}
