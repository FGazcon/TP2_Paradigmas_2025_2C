package Tablero;

import Tablero.Vertice.Vertice;

public class Arista {

    private Vertice destino;
    private Carretera carretera;

    public Arista(Vertice destino) {
        this.destino = destino;
        this.carretera = null;
    }

}
