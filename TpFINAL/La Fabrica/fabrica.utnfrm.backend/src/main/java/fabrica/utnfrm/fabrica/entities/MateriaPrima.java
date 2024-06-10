package fabrica.utnfrm.fabrica.entities;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class MateriaPrima {
    @Id
    private String nombre;
    private Integer existencia;
}
