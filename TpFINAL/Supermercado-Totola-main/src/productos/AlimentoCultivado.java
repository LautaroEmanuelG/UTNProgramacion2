package productos;

public class AlimentoCultivado extends Producto{

	private String tipo;
	
	public AlimentoCultivado() {
		super();
		tipo = " ";		
	}
	
	public AlimentoCultivado(String nombre, float precio, int stock, int id, String tipo) {
		super(nombre, precio, stock, id);
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return super.toString() + "TIPO: " + tipo.toUpperCase() + " ";
	}
	
}
