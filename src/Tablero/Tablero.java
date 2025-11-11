package Tablero;

import java.util.HashMap;

public class Tablero {

    private Hexagono[] hexagonos;
    private Vertice[] vertices;


    public Tablero() {

        this.vertices = Vertice.generarVertices();

        this.hexagonos = Hexagono.generar19Hexagonos(this.vertices);

    }

}
