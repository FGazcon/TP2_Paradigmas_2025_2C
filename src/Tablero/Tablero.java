package Tablero;

import java.util.HashMap;

public class Tablero {

    private Hexagono[] hexagonos;
    private HashMap<Vertice, HashMap<Vertice, Arista>> aristas;

    public Tablero() {

        Vertice[] vertices = new Vertice[54];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertice();
        }

        this.hexagonos = Hexagono.generar19Hexagonos(vertices);;

        this.aristas = new HashMap<Vertice, HashMap<Vertice, Arista>>();

        generarGrafo(vertices);

        for (int i = 0; i < vertices.length; i++) {
            System.out.println(i + " " + this.aristas.get(vertices[i]).size());
            System.out.println(this.aristas.get(vertices[i]));
        }

    }

    private void agregarArista(Vertice vertice1, Vertice vertice2) {

        Arista arista1 = new Arista(vertice2);
        Arista arista2 = new Arista(vertice1);

        this.aristas.get(vertice1).put(vertice2, arista1);
        this.aristas.get(vertice2).put(vertice1, arista2);

    }

    private void generarGrafo(Vertice[] vertices) {

        for (int i = 0; i < 54; i++) {
            this.aristas.put(vertices[i], new HashMap<Vertice, Arista>());
        }

        agregarArista(vertices[0], vertices[1]);
        agregarArista(vertices[0], vertices[29]);

        agregarArista(vertices[1], vertices[2]);

        agregarArista(vertices[2], vertices[3]);
        agregarArista(vertices[2], vertices[31]);

        agregarArista(vertices[3], vertices[4]);

        agregarArista(vertices[4], vertices[5]);
        agregarArista(vertices[4], vertices[33]);

        agregarArista(vertices[5], vertices[6]);

        agregarArista(vertices[6], vertices[7]);

        agregarArista(vertices[7], vertices[8]);
        agregarArista(vertices[7], vertices[34]);

        agregarArista(vertices[8], vertices[9]);

        agregarArista(vertices[9], vertices[10]);
        agregarArista(vertices[9], vertices[16]);

        agregarArista(vertices[10], vertices[11]);

        agregarArista(vertices[11], vertices[12]);

        agregarArista(vertices[12], vertices[13]);
        agregarArista(vertices[12], vertices[37]);

        agregarArista(vertices[13], vertices[14]);

        agregarArista(vertices[14], vertices[15]);
        agregarArista(vertices[14], vertices[39]);

        agregarArista(vertices[15], vertices[16]);

        agregarArista(vertices[16], vertices[17]);

        agregarArista(vertices[17], vertices[18]);
        agregarArista(vertices[17], vertices[40]);

        agregarArista(vertices[18], vertices[19]);

        agregarArista(vertices[19], vertices[20]);
        agregarArista(vertices[19], vertices[42]);

        agregarArista(vertices[20], vertices[21]);

        agregarArista(vertices[21], vertices[22]);

        agregarArista(vertices[22], vertices[44]);
        agregarArista(vertices[22], vertices[23]);

        agregarArista(vertices[23], vertices[24]);

        agregarArista(vertices[24], vertices[25]);
        agregarArista(vertices[24], vertices[45]);

        agregarArista(vertices[25], vertices[26]);

        agregarArista(vertices[26], vertices[27]);

        agregarArista(vertices[27], vertices[28]);
        agregarArista(vertices[27], vertices[46]);

        agregarArista(vertices[28], vertices[29]);

        agregarArista(vertices[29], vertices[30]);

        agregarArista(vertices[30], vertices[31]);
        agregarArista(vertices[30], vertices[47]);

        agregarArista(vertices[31], vertices[32]);

        agregarArista(vertices[32], vertices[33]);
        agregarArista(vertices[32], vertices[48]);

        agregarArista(vertices[33], vertices[34]);

        agregarArista(vertices[34], vertices[35]);

        agregarArista(vertices[35], vertices[36]);
        agregarArista(vertices[35], vertices[49]);

        agregarArista(vertices[36], vertices[37]);

        agregarArista(vertices[37], vertices[38]);

        agregarArista(vertices[38], vertices[39]);
        agregarArista(vertices[38], vertices[50]);

        agregarArista(vertices[39], vertices[40]);

        agregarArista(vertices[40], vertices[41]);

        agregarArista(vertices[41], vertices[42]);
        agregarArista(vertices[41], vertices[51]);

        agregarArista(vertices[42], vertices[43]);

        agregarArista(vertices[43], vertices[44]);

        agregarArista(vertices[44], vertices[45]);
        agregarArista(vertices[44], vertices[52]);

        agregarArista(vertices[45], vertices[46]);

        agregarArista(vertices[46], vertices[47]);

        agregarArista(vertices[47], vertices[53]);

        agregarArista(vertices[48], vertices[49]);
        agregarArista(vertices[48], vertices[53]);

        agregarArista(vertices[49], vertices[50]);

        agregarArista(vertices[50], vertices[51]);

        agregarArista(vertices[51], vertices[52]);

        agregarArista(vertices[52], vertices[53]);
    }

}
