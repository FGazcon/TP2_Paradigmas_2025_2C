package Ladron;

import Tablero.Hexagono;

public class Ladron {

    private int numeroDeHexagono;
    private static Ladron ladron =  new Ladron();

    private Ladron() {
        this.numeroDeHexagono = 0;
    }

    public static Ladron getLadron(){
        return ladron;
    }

    public void ubicarseEn(int numeroDeHexagono){
        this.numeroDeHexagono = numeroDeHexagono;
    }

    //public Ladron();

    public void moverLadron(int numeroDeHexagono){
        this.numeroDeHexagono = numeroDeHexagono;
    }

}
