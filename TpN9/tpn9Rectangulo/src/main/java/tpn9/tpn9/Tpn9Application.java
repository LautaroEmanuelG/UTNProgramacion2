package tpn9.tpn9;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// public class Tpn9Application {

// 	public static void main(String[] args) {
// 		SpringApplication.run(Tpn9Application.class, args);
// 		System.out.println("Hola mundo!");
// 	}

// }

@SpringBootApplication
public class Tpn9Application {
	public static void main(String[] args) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		String pregunta = JOptionPane.showInputDialog("1.guarda el objeto?\n0.Recupera el objetos?");

		Rectangulo r = new Rectangulo();
		if (pregunta.trim().equals("1")) {
			String anchura = JOptionPane.showInputDialog("Ingrese el ancho:");
			r.setAncho(Integer.parseInt(anchura));
			String altura = JOptionPane.showInputDialog("Ingrese el alto:");
			r.setAlto(Integer.parseInt(altura));
			System.out
					.println("ancho: " + r.getAncho() + " alto: " + r.getAlto() + "\nel área es: " + r.calcularArea());
			r.guardaRect();
		} else {
			r = r.consultaRect();
			System.out
					.println("ancho: " + r.getAncho() + " alto: " + r.getAlto() + "\nel área es: " + r.calcularArea());
		}
	}
}
