package Ladron;

import Tablero.Hexagono;

public class Ladron {

    private Hexagono hexagono;

    public Ladron(Hexagono hexagonoDesierto) {
        this.hexagono = hexagonoDesierto;
    }

    //public Ladron();

    public void moverLadron(Hexagono destino){
        this.hexagono = destino;
    }

}
