package tpn7.prog2.entities;

import lombok.Getter;
import lombok.Setter;
import tpn7.prog2.interfaces.Contrato;

@Getter
@Setter
public class Atleta implements Contrato{
    private String nombre;
    private int DNI;
    private int edad;
    private double altura;
    private double peso;


    public Atleta(String nombre, int DNI, int edad, double altura, double peso) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
    }

    @Override
    public float tomarPulsaciones() {
        return 220 - this.edad;
    }

    @Override
    public float calcularIMC() {
        return (float) (this.peso / Math.pow(this.altura, 2));
    }

    @Override
    public boolean hayPesoExtra(float imc) {
        return imc > 25 && imc < 29.9;
    }
}
