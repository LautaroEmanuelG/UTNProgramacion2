package productos;

import java.util.ArrayList;

public class ListaProducto<E extends Producto> {

	protected ArrayList<E> listaProductos;

	public ListaProducto() {
		listaProductos = new ArrayList<E>();
	}

	public void agregarProducto(E prod) {
		listaProductos.add(prod);
	}

	public void sacarProducto(E prod) {
		listaProductos.remove(prod);
	}

	public String mostrar() {
		StringBuilder lista = new StringBuilder();
		
		for (E e : listaProductos) {
			lista.append(e.toString());
		}
		
		return lista.toString();
	}
	
	public E get (int index)
	{
		return listaProductos.get(index);
	}
	
	public int size()
	{
		return listaProductos.size();
	}

	public boolean buscar(String nombre) { // retorna un boolean si encuentra el producto pasado por parametro

		boolean flag = false;
		int i = 0;

		while (i < listaProductos.size() && flag == false) {

			if (listaProductos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				flag = true;
			}
			i++;
		}
		return flag;
	}

	public Producto retornarProducto(String nombre) // retorna el producto buscado si es que existe, si no retorna null
	{
		for (E e : listaProductos) {
			if (e.getNombre().equalsIgnoreCase(nombre))
				return e;
		}
		
		return null;		
	}
	
	public void buscarRepone(String nombre, int cantidad) { // busca el producto pasado por parametro y si lo encuentra incrementa x cantidad en su stock

		boolean flag = false;
		int i = 0;

		while (i < listaProductos.size() && flag == false) {

			if (listaProductos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				flag = true;
				listaProductos.get(i).agregarStock(cantidad);
			}
			i++;
		}

	}

}
