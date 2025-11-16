package Tablero;

import Tablero.Vertice.Vertice;

public class Arista {

    private Vertice destino;

    public Arista(Vertice destino) {
        this.destino = destino;
    }

    public void bloquearDestino(){
        this.destino.bloquearse();
    }

}
