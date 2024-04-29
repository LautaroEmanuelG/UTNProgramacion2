package tp5.tp5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tp5.tp5.entities.Chofer;
import tp5.tp5.entities.Empresa;
import tp5.tp5.entities.Equipaje;
import tp5.tp5.entities.Taxi;
import tp5.tp5.entities.Trabajador;
import tp5.tp5.entities.Viajero;

@SpringBootApplication
public class Tp5Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp5Application.class, args);
		System.out.println("Hello World");

		//escriba la línea de código necesaria para mostrar desde un objeto de la relación, 
		//una característica del objeto del atributo de relación.
		//Imagine la relación implementada usando arreglos.

		//PRIMER EJERCICIO
		Trabajador trabajador1 = new Trabajador();
		trabajador1.setDni(12345678);
		Empresa empresa = new Empresa();
		empresa.setEmpleados(new Trabajador[] {trabajador1});
		empresa.setRSocial("Empresa 1");
		trabajador1.setEmpleador(empresa);

		System.out.println("Dni empleado 1 desde empresa " + empresa.getEmpleados()[0].getDni());
		// System.out.println("Dni empleado 2 desde empresa " + empresa.getEmpleados()[1].getDni());
		System.out.println("RSocial desde empleado 1: " + trabajador1.getEmpleador().getRSocial());	


		//SEGUNDO EJERCICIO
		Viajero viajero = new Viajero();
		viajero.setNombre("Juan");
		viajero.setSexo('M');

		Equipaje equipaje1 = new Equipaje();
		equipaje1.setPeso(10);
		equipaje1.setTipo("Carry-on");
		Equipaje equipaje2 = new Equipaje();
		equipaje2.setPeso(20);
		equipaje2.setTipo("Checked");
		Equipaje equipaje3 = new Equipaje();
		equipaje3.setPeso(30);
		equipaje3.setTipo("Carry-on");
		// viajero.setGuardaEquipaje(new Equipaje[] {equipaje1, equipaje2});

		System.out.println("Equipaje 1 de Juan: " + viajero.getGuardaEquipaje()[0].getTipo());
		System.out.println("Equipaje 2 de Juan: " + viajero.getGuardaEquipaje()[1].getTipo());


		//TERCER EJERCICIO
		Taxi taxi1 = new Taxi();
		taxi1.setMatricula("ABC123");
		Taxi taxi2 = new Taxi();
		taxi2.setMatricula("DEF456");
		Taxi taxi3 = new Taxi();
		taxi3.setMatricula("GHI789");
		Chofer chofer = new Chofer();
		chofer.setNombre("Pedro");
		chofer.setConduce(new Taxi[] {taxi1,taxi2});

		System.out.println("Matricula taxi 1 de Pedro: " + chofer.getConduce()[0].getMatricula());
		System.out.println("Matricula taxi 2 de Pedro: " + chofer.getConduce()[1].getMatricula());
	
		//Ejercicio 3
		trabajador1.setEmpleador(empresa);
		System.out.println("Trabajador, relacionado con 1 objeto Empresa"+ trabajador1.getEmpleador().getRSocial());
		viajero.setGuardaEquipaje(new Equipaje[] {equipaje1,equipaje2});
		System.out.println("Viajero, relacionado con 2 objetos Equipaje"+ viajero.getGuardaEquipaje()[0].getTipo());
		chofer.setConduce(new Taxi[] {taxi1,taxi2,taxi3});
		System.out.println("Chofer, relacionado con 3 objetos Taxi"+ chofer.getConduce()[0].getMatricula());

		//Ejercicio 4
		System.out.println("Razon Social desde Trabajador"
		+ trabajador1.getEmpleador().getRSocial());
		System.out.println("Peso de los Equipajes desde Viajero: "
		+ viajero.getGuardaEquipaje()[0].getPeso()+"--"
		+ viajero.getGuardaEquipaje()[1].getPeso());
		System.out.println("Matriculas de los Taxis desde Chofer: "
		+ chofer.getConduce()[0].getMatricula()+"--"
		+ chofer.getConduce()[1].getMatricula()+"--"
		+ chofer.getConduce()[2].getMatricula());
		if ( empresa.getEmpleados()[0].getDni() != 0){
			System.out.println("Dni de Trabajador 1 desde Empresa: "
			+ empresa.getEmpleados()[0].getDni());
		}
	}
}
