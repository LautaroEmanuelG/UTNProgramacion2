package JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import productos.AlimentoCultivado;
import productos.Congelado;
import productos.Limpieza;
import productos.ListaProducto;
import java.math.BigDecimal;
import java.util.Iterator;

public class ControladoraJSON {

	public static void crearJSONObjListaProductos(ListaProducto<AlimentoCultivado> alCultivados,
			ListaProducto<Limpieza> limpieza, ListaProducto<Congelado> congelados) {
		try {

			// json object general
			JSONObject productos = new JSONObject();

			// json array de alimentos cultivados

			JSONArray alCultivadosJ = new JSONArray();

			for (int i = 0; i < alCultivados.size(); i++) {

				JSONObject nuevoProd = new JSONObject();

				nuevoProd.put("Nombre", alCultivados.get(i).getNombre());
				nuevoProd.put("Precio", alCultivados.get(i).getPrecio());
				nuevoProd.put("Stock", alCultivados.get(i).getStock());
				nuevoProd.put("ID", alCultivados.get(i).getId());
				nuevoProd.put("Tipo", alCultivados.get(i).getTipo());

				alCultivadosJ.put(nuevoProd);
			}

			productos.put("Alimentos Cultivados", alCultivadosJ);

			// json array de articulos de limpieza

			JSONArray limpiezaJ = new JSONArray();

			for (int i = 0; i < limpieza.size(); i++) {

				JSONObject nuevoProd = new JSONObject();

				nuevoProd.put("Nombre", limpieza.get(i).getNombre());
				nuevoProd.put("Precio", (double) (limpieza.get(i).getPrecio()));
				nuevoProd.put("Stock", limpieza.get(i).getStock());
				nuevoProd.put("ID", limpieza.get(i).getId());
				nuevoProd.put("Tipo de aseo", limpieza.get(i).getTipoDeAseo());

				limpiezaJ.put(nuevoProd);
			}

			productos.put("Limpieza", limpiezaJ);

			// json array de congelados

			JSONArray congeladosJ = new JSONArray();

			for (int i = 0; i < congelados.size(); i++) {
				JSONObject nuevoProd = new JSONObject();

				nuevoProd.put("Nombre", congelados.get(i).getNombre());
				nuevoProd.put("Precio", congelados.get(i).getPrecio());
				nuevoProd.put("Stock", congelados.get(i).getStock());
				nuevoProd.put("ID", congelados.get(i).getId());
				nuevoProd.put("Temperatura adecuada", congelados.get(i).getTemperaturaAdecuada());

				congeladosJ.put(nuevoProd);
			}

			productos.put("Congelados", congeladosJ);

			JsonUtiles.grabar(productos, "listaProductos");

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void JsonALista(ListaProducto<AlimentoCultivado> alCultivados, ListaProducto<Limpieza> limpieza,
			ListaProducto<Congelado> congelados) {
		try {

			String archivoProductos = JsonUtiles.leer("listaProductos");
			JSONObject productos = new JSONObject(archivoProductos);

			JSONArray limpiezaArr = productos.getJSONArray("Limpieza");

			for (int i = 0; i < limpiezaArr.length(); i++) {

				JSONObject prod = limpiezaArr.getJSONObject(i);

				limpieza.agregarProducto(new Limpieza(prod.getString("Nombre"),
						BigDecimal.valueOf(prod.getDouble("Precio")).floatValue(), prod.getInt("Stock"),
						prod.getInt("ID"), prod.getString("Tipo de aseo")));
			}

			JSONArray congeladosArr = productos.getJSONArray("Congelados");

			for (int i = 0; i < congeladosArr.length(); i++) {

				JSONObject prod = congeladosArr.getJSONObject(i);

				congelados.agregarProducto(new Congelado(prod.getString("Nombre"),
						BigDecimal.valueOf(prod.getDouble("Precio")).floatValue(), prod.getInt("Stock"),
						prod.getInt("ID"), BigDecimal.valueOf(prod.getDouble("Temperatura adecuada")).floatValue()));
			}

			JSONArray alCultivadosArr = productos.getJSONArray("Alimentos Cultivados");

			for (int i = 0; i < alCultivadosArr.length(); i++) {

				JSONObject prod = alCultivadosArr.getJSONObject(i);

				alCultivados.agregarProducto(new AlimentoCultivado(prod.getString("Nombre"),
						BigDecimal.valueOf(prod.getDouble("Precio")).floatValue(), prod.getInt("Stock"),
						prod.getInt("ID"), prod.getString("Tipo")));
			}

		} catch (JSONException e) {
			// TODO: handle exception
		}
	}

}
