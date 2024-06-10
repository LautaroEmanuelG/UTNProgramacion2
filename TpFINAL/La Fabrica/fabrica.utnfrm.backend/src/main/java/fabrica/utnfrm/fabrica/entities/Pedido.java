package fabrica.utnfrm.fabrica.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import fabrica.utnfrm.fabrica.enums.Estado;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCreate;
    //PRODUCIENDO por defecto
    private Estado state;
    //False por defecto
    private Boolean delete = false;
}
