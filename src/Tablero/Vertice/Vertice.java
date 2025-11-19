package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Produccion.Recurso;
import Tablero.Arista;
import Tablero.GeneradorGrafo;
import Tablero.Vertice.Estructura.Estructura;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    private Estructura estructura;
    private List<Arista> aristas;
    private int numeroDeVertice;
    private Estado estado;


    public Vertice(int numeroDeVertice) {
        this.estructura = null;
        this.aristas = new ArrayList<Arista>();
        this.numeroDeVertice = numeroDeVertice;
        this.estado = new Vacio();
    }

    public Vertice() {
        this.estructura = null;
        this.aristas = new ArrayList<Arista>();
        this.numeroDeVertice = 0;
        this.estado = new Vacio();
    }

    public void ubicarEstructura(Estructura estructura) {

        this.estado.intentarUbicarEstructura(estructura, this);

    }


    public void bloquearse() {
        this.estado = new Bloqueado();
    }

    public void ocuparse(Estructura estructura) {
        this.estado = new Ocupado();
        this.estructura = estructura;
    }

    public void bloquearAdyacentes() {
        for (Arista arista : aristas) {
            arista.bloquearDestino();
        }
    }

    public void darRecurso(Recurso recurso) {
        this.estructura.entregarRecursos(recurso);
    }

    public boolean numeroDeVerticeEs(int numeroDeVertice) {
        return (this.numeroDeVertice == numeroDeVertice);
    }

    public static void agregarArista(Vertice vertice1, Vertice vertice2) {

        Arista arista1 = new Arista(vertice2);
        Arista arista2 = new Arista(vertice1);

        vertice1.aristas.add(arista1);
        vertice2.aristas.add(arista2);

    }

    public static Vertice[] generarVertices() {

        Vertice[] vertices = new Vertice[54];
        for (int i = 0; i < 54; i++) {
            vertices[i] = new Vertice(i);
        }

        GeneradorGrafo.generarGrafo(vertices);

        for (int i = 0; i < vertices.length; i++) {
            System.out.println(vertices[i].numeroDeVertice + " " +  vertices[i]);
        }

        return vertices;

    }
}

