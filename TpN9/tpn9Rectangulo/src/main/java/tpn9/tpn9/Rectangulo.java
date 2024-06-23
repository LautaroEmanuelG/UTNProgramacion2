
package tpn9.tpn9;

import java.io.*;
// import javax.swing.*;

import lombok.Data;

@Data
public class Rectangulo implements java.io.Serializable {
    private int ancho;
    private int alto;

    public Rectangulo() {
    }

    public Rectangulo(int an, int al) {
        ancho = an;
        alto = al;
    }

    public void guardaRect() throws FileNotFoundException, IOException {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("figura.txt"));
            System.out.println("se guardó un objeto compuesto\n");
            salida.writeObject(this);// writeUnshared(this);
            salida.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
            e.getMessage();
        } catch (IOException e) {
            System.out.println("Hubo un error al guardar");
            e.getMessage();
        }
    }

    public Rectangulo consultaRect() throws FileNotFoundException, IOException,
            ClassNotFoundException, EOFException {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("figura.txt"));
        Rectangulo obj1 = (Rectangulo) entrada.readObject();
        System.out.println("------------------------------");
        System.out.println("Recuperados: ancho: " + obj1.ancho + " alto: " + obj1.alto);
        System.out.println("------------------------------");
        entrada.close();
        return obj1;
    }

    public int calcularArea() {
        int area = this.alto * this.ancho;
        return area;
    }
}