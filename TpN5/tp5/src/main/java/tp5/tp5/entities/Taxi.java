package tp5.tp5.entities;

import lombok.Data;

@Data
public class Taxi {
    private String matricula;

    public String printMatricula() {
        return this.matricula;
    }
}
