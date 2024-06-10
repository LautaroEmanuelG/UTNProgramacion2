package carrito;

import java.util.Objects;

public class ItemPedido {
	// atributos
	
	private String nombre;
	private float precioUnitario;
	private int cantidad;
	
	// constructores
	
	public ItemPedido()
	{
		nombre = "";
		precioUnitario = 0;
		cantidad = 0;
	}
	
	public ItemPedido(String nombre, float precioUnitario, int cantidad)
	{
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
	}
	
	// gets & sets
	
	public int getCantidad() {
		return cantidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	
	// metodos
	
	public float precioTotal()
	{
		return precioUnitario * cantidad;
	}
	
	@Override
	public String toString() {
		return nombre+"\t$"+precioUnitario+"\t"+cantidad;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return cantidad == other.cantidad && Objects.equals(nombre, other.nombre)
				&& Float.floatToIntBits(precioUnitario) == Float.floatToIntBits(other.precioUnitario);
	}
	
	
	

}
