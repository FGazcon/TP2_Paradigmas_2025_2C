package Tablero;

import java.util.HashMap;

public class Tablero {

    private Hexagono[] hexagonos;
    private Vertice[] vertices;


    public Tablero() {

        Vertice[] vertices = new Vertice[54];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertice();
        }

        this.hexagonos = Hexagono.generar19Hexagonos(vertices);

        this.vertices = Vertice.generarVertices();

    }

}
