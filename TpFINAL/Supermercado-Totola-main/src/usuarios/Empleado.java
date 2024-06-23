package usuarios;

import java.io.Serializable;

import interfaces.I_calcularMonto;

public class Empleado extends Usuario implements I_calcularMonto{

    private float sueldo;
    private String horario;
    private float pagoPorHoras;
	private int horasTrabajadas;
    private int horasExtras;
    
	public Empleado(String nombre, String apellido, int edad, String dni, String nombreDeUsuario, String contraseña, String horario,float pagoPorHoras) {
		super(nombre, apellido, edad, dni, nombreDeUsuario, contraseña);
		sueldo = 0;
		this.horario = horario;
		this.pagoPorHoras = pagoPorHoras;
		horasTrabajadas = 0;
		horasExtras = 0;
	}

	@Override
	public String toString() {
		return super.toString()+"\nSUELDO:" +sueldo + "\nHORARIO:" + horario + "\nHORAS TRABAJADAS:" + horasTrabajadas
				+ "\nHORAS EXTRAS:" + horasExtras;
	}
	
	public void setHorasExtras(int horasExtras) {
		this.horasExtras = horasExtras;
	}
	
	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}
	
	public float getSueldo() {
		return sueldo;
	}
	
	public float aCobrar() // asigna el sueldo y lo retorna para mostrar
    {
        sueldo = ((horasTrabajadas*pagoPorHoras)+(horasExtras*pagoPorHoras*2));
    	
    	return sueldo;
    }
}
