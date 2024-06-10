package usuarios;

import java.io.Serializable;

public class Cajero extends Empleado{

	int cajaAAtender;

	public Cajero(String nombre, String apellido, int edad, String dni, String nombreDeUsuario, String contraseña,
			 String horario, int cajaAAtender,float pagoPorHoras) {
		
		super(nombre, apellido, edad, dni, nombreDeUsuario, contraseña, horario,pagoPorHoras);
		
		this.cajaAAtender = cajaAAtender;
	}

	public void AsignarCaja(int caja)
	{
		cajaAAtender=caja;
	}

	public int getCajaAAtender() {
		return cajaAAtender;
	}
	
	
	
	

}
