package Tablero;

import Tablero.Vertice.Vertice;

public class Tablero {

    private Hexagono[] hexagonos;


    public Tablero() {

        this.hexagonos = Hexagono.generar19Hexagonos();

    }

    public Hexagono buscarDesierto() {

        Hexagono hexagonoDesierto = null;

        for (int i = 0; i < 19; i++) {

            if (this.hexagonos[i].esDesierto()) {
                hexagonoDesierto = this.hexagonos[i];
            }

        }

        return hexagonoDesierto;

    }

}
