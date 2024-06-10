package fabrica.utnfrm.fabrica.entities;


import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Orden {
    @Id
    private String producto_a_fabricar;
    private Boolean cumplida;
    private Integer cantidad;

    private Producto producto;
}
