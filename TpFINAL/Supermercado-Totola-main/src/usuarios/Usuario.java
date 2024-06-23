package usuarios;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public abstract class Usuario implements Serializable{
	
	private String nombre;
	private String apellido;
	private int edad;
	private String dni;
	private String nombreDeUsuario;
	private String contraseña;
	private Date fechaDeCreacion;
	
	public Usuario(String nombre, String apellido, int edad, String dni, String nombreDeUsuario, String contraseña) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.dni = dni;
		this.nombreDeUsuario = nombreDeUsuario;
		this.contraseña = contraseña;
		fechaDeCreacion = Calendar.getInstance().getTime();
	}

	@Override
	public String toString() {
		return "\nDATOS DE " + nombre.toUpperCase() + " " + apellido.toUpperCase() + "\nEDAD: " + edad + "\nDNI: " + dni
				+ "\nNOMBRE DE USUARIO: " + nombreDeUsuario + "\nCONTRASEÑA: " + contraseña + "\nFECHA DE CREACION: "
				+ fechaDeCreacion.toString();
	}

	public void CambiarContraseña(String contraseña) {
		
		this.contraseña = contraseña;
		
	}

	public String getNombreYApellido() {
		return nombre+apellido;
	}
	
	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombreDeUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		Usuario other = (Usuario) obj;
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (Objects.equals(dni, other.dni) || Objects.equals(nombreDeUsuario, other.nombreDeUsuario))
			return true;
		else
			return false;
		
	}
	
	
	

}
