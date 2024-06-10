package fabrica.utnfrm.fabrica.entities;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Producto {
    @Id
    private String nombre;
    private Integer stock;
    private HashMap<MateriaPrima,Integer> mat_primas;

    public void procesar_ordenes_pendientes() {
        if (this.stock > 0) {
            System.out.println("Procesando ordenes");
        }
    }

    public ArrayList<Producto> cantidad_productos(MateriaPrima mat_prima) {
        ArrayList<Producto> productosUtilizandoMateriaPrima = new ArrayList<Producto>();
        //Recorrer todas las materias primas de cada producto
        for (MateriaPrima materiaPrima : this.mat_primas.keySet()) {
            //Si la materia prima coincide con la materia prima especificada
            if (materiaPrima.getNombre().equals(mat_prima.getNombre())) {
                //Agregar el producto a la lista de productos que utilizan la materia prima
                productosUtilizandoMateriaPrima.add(this);
                break;
            }
        }
        //Ordenar la lista de productos en los que mas se utilice la materia prima en el hasmap
        productosUtilizandoMateriaPrima.sort((Producto p1, Producto p2) -> p2.getMat_primas().get(mat_prima).compareTo(p1.getMat_primas().get(mat_prima)));
    
        return productosUtilizandoMateriaPrima;
    }
}
