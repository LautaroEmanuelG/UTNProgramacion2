package productos;

public class Congelado extends Producto{

	private float temperaturaAdecuada;
	
	public Congelado() {
		super();
		temperaturaAdecuada = 0;
	}
	
	public Congelado(String nombre, float precio, int stock, int id, float temperaturaAdecuada) {
		super(nombre, precio, stock, id);
		this.temperaturaAdecuada = temperaturaAdecuada;
	}
	
	public float getTemperaturaAdecuada() {
		return temperaturaAdecuada;
	}

	@Override
	public String toString() {
		return super.toString() + "TEMPERATURA ADECUADA: " + temperaturaAdecuada + " ";
	}
	
}
