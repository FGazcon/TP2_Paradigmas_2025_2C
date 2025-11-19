package Tablero;

import Produccion.Recurso;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

import java.util.List;

public class Hexagono {

    private Vertice[] vertices;
    private Terreno terreno;
    private int numero;

    public Hexagono(Terreno terreno, int numero) {
        this.vertices = new Vertice[6];
        this.terreno = terreno;
        this.numero = numero;
    }

    public boolean contieneVertice(int numeroDeVertice){
        for(int i = 0; i < vertices.length; i++){
            if (vertices[i].numeroDeVerticeEs(numeroDeVertice)){
                return true;
            }
        }
        return false;
    }

    public void ubicarEstructura(Estructura estructura, int numeroDeVertice){
        for(int i = 0; i < vertices.length; i++){
            if (vertices[i].numeroDeVerticeEs(numeroDeVertice)){
                vertices[i].ubicarEstructura(estructura);
            }
        }
    }

    public void activarHexagono(int numero){
        if(this.numero == numero){
            for(int i = 0; i < vertices.length; i++){
                this.terreno.darRecurso(vertices[i]);
            }
        }
    }

    public void activarseParaVerticeEspecifico(int[] vertices_segundo_poblado) {
        for (int i = 0; i < vertices.length; i++){
            for (int j = 0; j < vertices_segundo_poblado.length; j++){
                if (vertices[i].numeroDeVerticeEs(vertices_segundo_poblado[j])){
                    this.terreno.darRecurso(vertices[i]);
                }
            }
        }
    }

    public void entregarRecursoAVertices(Recurso recurso){
        for(Vertice vertice: vertices){
            vertice.darRecurso(recurso);
        }
    }

    //Consultar si esto esta bien.
    public void setVertices(Vertice[] vertices) {
        this.vertices = vertices;
    }

    public static List<Hexagono> generar19Hexagonos() {

        List<Terreno> terrenos = Terreno.generar19Terrenos();


        List<Hexagono> hexagonos = MezcladorHexagonosNumerosYTerrenos.mezclarNumerosYHexagonos(Vertice.generarVertices(), terrenos);

        for (int i = 0; i < hexagonos.size(); i++) {
            System.out.println(hexagonos.get(i) + " " + i + " " + hexagonos.get(i).numero);
            for (Vertice vertice : hexagonos.get(i).vertices) {
                System.out.println(vertice);
            }
        }
        return hexagonos;

    }

}
