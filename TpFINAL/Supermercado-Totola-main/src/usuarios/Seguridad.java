package usuarios;

import java.io.Serializable;

public class Seguridad extends Empleado{

	private String zonaAvigilar;

	public Seguridad(String nombre, String apellido, int edad, String dni, String nombreDeUsuario, String contraseña, String horario, String zonaAvigilar,float pagoPorHoras) {
		
		super(nombre, apellido, edad, dni, nombreDeUsuario, contraseña, horario,pagoPorHoras);
		
		this.zonaAvigilar = zonaAvigilar;
	}

	@Override
	public String toString() {
		return super.toString() + "\nZONA A VIGILAR: " + zonaAvigilar;
	}
	
	public void asignarZona(String zonaAVigilar) {
		
		this.zonaAvigilar = zonaAVigilar;
		
	}

	public String getZonaAvigilar() {
		return zonaAvigilar;
	}

	
}
