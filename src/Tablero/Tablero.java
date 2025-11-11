package Tablero;

import Terreno.Desierto;

import java.util.HashMap;

public class Tablero {

    private Hexagono[] hexagonos;
    private Vertice[] vertices;


    public Tablero() {

        this.vertices = Vertice.generarVertices();

        this.hexagonos = Hexagono.generar19Hexagonos(this.vertices);

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



    public void moverLadron(Hexagono destino, Hexagono origen) {

    }

}
