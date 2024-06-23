package excepciones;

public class UsuarioIncorrectoException extends Exception{ // usuario incorrecto
	public UsuarioIncorrectoException(String message)
	{
		super(message);
	}
}
