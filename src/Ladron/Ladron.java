package Ladron;

import Tablero.Hexagono;

public class Ladron {

    private int numeroDeHexagono;
    private static Ladron ladron =  new Ladron();

    Ladron() {
        this.numeroDeHexagono = 0;
    }

    public static Ladron getLadron(){
        return ladron;
    }

    public void ubicarseEn(int numeroDeHexagono){
        this.numeroDeHexagono = numeroDeHexagono;
    }

    //public Ladron();

    public int moverLadron(int numeroDeHexagono){
        int aux = this.numeroDeHexagono;
        this.numeroDeHexagono = numeroDeHexagono;
        return aux;
    }

}
