package productos;

public class Limpieza extends Producto{

	private String tipoDeAseo;
	
	public Limpieza() {
		super();
		tipoDeAseo = " ";		
	}
	
	public Limpieza(String nombre, float precio, int stock, int id, String tipoDeAseo) {
		super(nombre, precio, stock, id);
		this.tipoDeAseo = tipoDeAseo;
	}
	
	public String getTipoDeAseo() {
		return tipoDeAseo;
	}

	@Override
	public String toString() {
		return super.toString() + "TIPO DE ASEO: " + tipoDeAseo.toUpperCase() + " ";
	}
	
}
