package fabrica.utnfrm.fabrica.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import fabrica.utnfrm.fabrica.enums.Estado;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Pedido pedido;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateStart;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateEnd;
    private Producto productoId;
    private Estado state;
    private Boolean delete = false;
}
