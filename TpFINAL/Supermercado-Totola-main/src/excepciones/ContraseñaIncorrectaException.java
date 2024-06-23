package excepciones;

public class ContraseñaIncorrectaException extends Exception{  // contrasena incorrecta
	public ContraseñaIncorrectaException(String message)
	{
		super(message);
	}
}
