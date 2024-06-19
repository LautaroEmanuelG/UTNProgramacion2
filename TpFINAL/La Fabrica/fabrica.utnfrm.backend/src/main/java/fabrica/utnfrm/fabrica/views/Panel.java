package fabrica.utnfrm.fabrica.views;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Panel implements java.io.Serializable {
    
    public void guardarOrden() throws FileNotFoundException, IOException{
        try{
            java.io.FileOutputStream orden = new java.io.FileOutputStream("orden.txt");
            java.io.ObjectOutputStream panel = new java.io.ObjectOutputStream(orden);
            panel.writeObject(this);
            panel.close();
            orden.close();
            System.out.println("Se guardo la orden en orden.ser");
        }catch(FileNotFoundException e){
            System.out.println("No se encontro el archivo");
    }catch(IOException e){
        System.out.println("Error de entrada/salida");
    }
    }
}
