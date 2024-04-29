package tp5.tp5.entities;

import lombok.Data;

@Data
public class Empresa {
    public String rSocial;
    public Trabajador[] empleados;
}
