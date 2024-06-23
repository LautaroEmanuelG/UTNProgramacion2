package ControlArchivos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import usuarios.*;

public class ControladoraArchivos {
	private static File fileClientes = new File("ListaClientes");
	private static File fileCajeros = new File("ListaCajeros");
	private static File fileGerente = new File("Gerente");
	private static File fileSeguridad = new File("ListaSeguridad");
	private static File fileRepositores = new File("ListaRepositores");

	public static HashMap<String, Usuario> leerUsuarios() { // retorna la lista de usuarios cargada con el archivo
		FileInputStream fileInputStream;
		HashMap<String, Usuario> listaUsuarios = new HashMap<String, Usuario>();
		int todoLeido = 0;

		while (todoLeido < 5) {
			try {
				if (fileClientes.exists() && todoLeido == 0) {
					fileInputStream = new FileInputStream(fileClientes);

					ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

					int lectura = 1;

					while (lectura == 1) {
						Cliente aux = (Cliente) inputStream.readObject();
						listaUsuarios.put(aux.getNombreDeUsuario(), aux);
					}

					fileInputStream.close();
					inputStream.close();
				} else if (fileCajeros.exists() && todoLeido == 1) {
					fileInputStream = new FileInputStream(fileCajeros);

					ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

					int lectura = 1;

					while (lectura == 1) {
						Cajero aux = (Cajero) inputStream.readObject();
						listaUsuarios.put(aux.getNombreDeUsuario(), aux);
					}

					fileInputStream.close();
					inputStream.close();
				} else if (fileGerente.exists() && todoLeido == 2) {
					fileInputStream = new FileInputStream(fileGerente);

					ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

					int lectura = 1;

					while (lectura == 1) {
						Gerente aux = (Gerente) inputStream.readObject();
						listaUsuarios.put(aux.getNombreDeUsuario(), aux);
					}

					fileInputStream.close();
					inputStream.close();
				} else if (fileSeguridad.exists() && todoLeido == 3) {
					fileInputStream = new FileInputStream(fileSeguridad);

					ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

					int lectura = 1;

					while (lectura == 1) {
						Seguridad aux = (Seguridad) inputStream.readObject();
						listaUsuarios.put(aux.getNombreDeUsuario(), aux);
					}

					fileInputStream.close();
					inputStream.close();
				} else if (fileSeguridad.exists() && todoLeido == 4) {
					fileInputStream = new FileInputStream(fileRepositores);

					ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

					int lectura = 1;

					while (lectura == 1) {
						Repositor aux = (Repositor) inputStream.readObject();
						listaUsuarios.put(aux.getNombreDeUsuario(), aux);
					}

					fileInputStream.close();
					inputStream.close();
				}

			} catch (EOFException e) {
				// TODO Auto-generated catch block
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			} catch (NotSerializableException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			todoLeido++;
		}

		return listaUsuarios;
	}

	public static void guardarUsuarios(HashMap<String, Usuario> listaUsuarios) { // guarda la lista de usuarios en un archivo
		try {
			FileOutputStream fileOutputStream_Clientes = new FileOutputStream(fileClientes);
			FileOutputStream fileOutputStream_Gerente = new FileOutputStream(fileGerente);
			FileOutputStream fileOutputStream_Cajero = new FileOutputStream(fileCajeros);
			FileOutputStream fileOutputStream_Repositor = new FileOutputStream(fileRepositores);
			FileOutputStream fileOutputStream_Seguridad = new FileOutputStream(fileSeguridad);
			ObjectOutputStream objectOutputStream = null;
			Usuario i;

			for (Map.Entry<String, Usuario> fila : listaUsuarios.entrySet()) {
				i = fila.getValue();

				if (i instanceof Cliente) {
					objectOutputStream = new ObjectOutputStream(fileOutputStream_Clientes);

					objectOutputStream.writeObject(i);

				} else if (i instanceof Cajero) {
					objectOutputStream = new ObjectOutputStream(fileOutputStream_Cajero);

					objectOutputStream.writeObject(i);

				} else if (i instanceof Gerente) {
					objectOutputStream = new ObjectOutputStream(fileOutputStream_Gerente);

					objectOutputStream.writeObject(i);

				} else if (i instanceof Repositor) {
					objectOutputStream = new ObjectOutputStream(fileOutputStream_Repositor);

					objectOutputStream.writeObject(i);
				} else {
					objectOutputStream = new ObjectOutputStream(fileOutputStream_Seguridad);

					objectOutputStream.writeObject(i);
				}
			}

			objectOutputStream.close();
			fileOutputStream_Cajero.close();
			fileOutputStream_Clientes.close();
			fileOutputStream_Gerente.close();
			fileOutputStream_Repositor.close();
			fileOutputStream_Seguridad.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
