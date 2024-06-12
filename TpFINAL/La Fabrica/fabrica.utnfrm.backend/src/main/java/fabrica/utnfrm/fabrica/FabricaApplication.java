package fabrica.utnfrm.fabrica;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fabrica.utnfrm.fabrica.entities.MateriaPrima;
import fabrica.utnfrm.fabrica.entities.Orden;
import fabrica.utnfrm.fabrica.entities.Producto;

@SpringBootApplication
public class FabricaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabricaApplication.class, args);
		System.out.println("Hola mundo");

		
		MateriaPrima mineralBronce = new MateriaPrima("mineralBronce", 100);
		HashMap<MateriaPrima,Integer> matPrimasBronce = new HashMap<MateriaPrima,Integer>();
		matPrimasBronce.put(mineralBronce, 10);
		Producto bronce = new Producto();
		bronce.setNombre("Bronce");
		bronce.setStock(10);
		bronce.setMat_primas(matPrimasBronce);

		MateriaPrima mineralPlata = new MateriaPrima("mineralPlata", 50);
		HashMap<MateriaPrima,Integer> matPrimasPlata = new HashMap<MateriaPrima,Integer>();
		matPrimasPlata.put(mineralPlata, 10);
		Producto plata = new Producto();
		plata.setNombre("Plata");
		plata.setStock(5);
		plata.setMat_primas(matPrimasPlata);

		MateriaPrima mineralOro = new MateriaPrima("mineralOro", 10);
		HashMap<MateriaPrima,Integer> matPrimasOro = new HashMap<MateriaPrima,Integer>();
		matPrimasOro.put(mineralOro, 10);
		Producto oro = new Producto();
		oro.setNombre("Oro");
		oro.setStock(1);
		oro.setMat_primas(matPrimasOro);

		Orden orden1 = new Orden(oro, 1);
		Orden orden2 = new Orden(plata, 1);
		Orden orden3 = new Orden(bronce, 1);
		
		System.out.println(orden1.getProducto().getNombre());
		System.out.println(orden2.getProducto().getNombre());
		System.out.println(orden3.getProducto().getNombre());

		System.out.println(orden1.getCumplida());
		//TODO revisar flujo luego de orden para cargarlas y verificar que se pueden hacer
	}

}
