package mercado;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import ControlArchivos.ControladoraArchivos;
import JSON.ControladoraJSON;
import excepciones.*;
import usuarios.*;
import productos.*;


public class Mercado {
	// atributos

	private String nombre;
	private HashMap<String, Usuario> listaUsuarios;
	public ListaProducto<Congelado> congelados;
	private ListaProducto<AlimentoCultivado> alimentosCultivados;
	private ListaProducto<Limpieza> limpieza;

	// constructores

	public Mercado(String nombre) {
		this.nombre = nombre;
		listaUsuarios = ControladoraArchivos.leerUsuarios(); // se cargan los usuarios
		congelados = new ListaProducto<Congelado>();
		alimentosCultivados = new ListaProducto<AlimentoCultivado>();
		limpieza = new ListaProducto<Limpieza>();
		ControladoraJSON.JsonALista(alimentosCultivados, limpieza, congelados); // se cargan las listas de productos
	}

	// metodos
	
	public void guardarUsuarios ()
	{
		ControladoraArchivos.guardarUsuarios(listaUsuarios);
	}
	
	public void crearJSONListas() 
	{
		ControladoraJSON.crearJSONObjListaProductos(alimentosCultivados, limpieza, congelados);
	}

	public Usuario login(String usuario, String contraseña) // comprueba si el nombre de usuario existe y la contraseña es correcta
			throws UsuarioIncorrectoException, ContraseñaIncorrectaException {
		if (!listaUsuarios.containsKey(usuario))
			throw new UsuarioIncorrectoException("EL USUARIO NO EXISTE.");

		if (!listaUsuarios.get(usuario).getContraseña().equalsIgnoreCase(contraseña))
			throw new ContraseñaIncorrectaException("LA CONTRASEÑA ES INCORRECTA.");

		return listaUsuarios.get(usuario);
	}

	public boolean agregarUsuario(Usuario usuario) throws UsuarioIncorrectoException { 
		if (listaUsuarios.containsKey(usuario.getNombreDeUsuario()))        // si no existe el usuario, lo agrega
			throw new UsuarioIncorrectoException("USUARIO YA EXISTE.");

		listaUsuarios.put(usuario.getNombreDeUsuario(), usuario);
		return true;
	}

	public boolean existeUsuario(String nombreUsuario) {
		return listaUsuarios.containsKey(nombreUsuario);
	}

	public String mostrarTodosLosProductos() {
		StringBuilder lista = new StringBuilder();

		lista.append("CONGELADOS..\n" + congelados.mostrar()+"\n\n");
		lista.append("ALIMENTOS CULTIVADOS..\n" + alimentosCultivados.mostrar()+"\n\n");
		lista.append("ARTICULOS DE LIMPIEZA..\n" + limpieza.mostrar()+"\n\n");

		return lista.toString();
	}

	public boolean existeOno(String nombre) { // retorna un boolean si existe el producto con el nombre pasado por parametro

		boolean flag = congelados.buscar(nombre);

		if (flag == false) {
			flag = alimentosCultivados.buscar(nombre);
		}

		if (flag == false) {
			flag = limpieza.buscar(nombre);
		}

		return flag;
	}

	public Producto retornarProducto(String nombre)
	{
		Producto prod = congelados.retornarProducto(nombre);
		
		if (prod == null)
			prod = alimentosCultivados.retornarProducto(nombre);
		
		if (prod == null)
			prod = limpieza.retornarProducto(nombre);
		
		return prod;
	}
	
	public void reponer(String nombre, int cantidad) {  // busca el producto y si lo encuentra aumenta x cantidad en el stock

		congelados.buscarRepone(nombre, cantidad);
		alimentosCultivados.buscarRepone(nombre, cantidad);
		limpieza.buscarRepone(nombre, cantidad);
		
	}

	public void agregarProd(Producto prod, int tipo) { // agrega el producto a la lista correspondiente (pasada por parametro)
		
		if(tipo == 1) {
			congelados.agregarProducto((Congelado)prod);
		}
		else if(tipo == 2) {
			alimentosCultivados.agregarProducto((AlimentoCultivado)prod);
		}
		else {
			limpieza.agregarProducto((Limpieza)prod);
		}
	}
	
	public String mostrarEmpleados()
	{
		StringBuilder listaEmp = new StringBuilder();
		Iterator<Entry<String, Usuario>> it = listaUsuarios.entrySet().iterator();
		
		while (it.hasNext())
		{
			Entry<String, Usuario> fila = it.next();
			if (fila.getValue() instanceof Empleado)
				listaEmp.append(fila.getValue().toString()+"\n");
		}
		
		return listaEmp.toString();
	}
	
	public Empleado buscarEmpleado(String nombreUsuario)
	{
		return (Empleado)listaUsuarios.get(nombreUsuario);
	}
	
	public boolean eliminarEmpleado(String nombreUsuario)
	{
		if (listaUsuarios.remove(nombreUsuario) == null)
			return false;
		else
			return true;
	}
	
	/// gets
	
	public String getNombre() {
		return nombre;
	}
}
