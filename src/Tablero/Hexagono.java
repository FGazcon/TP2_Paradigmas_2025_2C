package Tablero;

import Errores.DesiertoNoProduceNada;
import Jugador.Jugador;
import Recurso.Recurso;
import Tablero.Factory.Factory_MapaBasico;
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

    public void activarHexagonoParaVerticeEspecifico(int vertices_segundo_poblado) {

        for (Vertice vertice : vertices) {
            if (vertice.numeroDeVerticeEs(vertices_segundo_poblado)) {

                try{
                    Recurso recurso = this.terreno.darRecurso();
                    vertice.darRecurso(recurso);
                } catch (DesiertoNoProduceNada e) {
                    System.out.println("Se intento generar recursos del Desierto");
                }

            }
        }
    }

    public void activarHexagonoNumero(int numero){
        if (this.numero == numero){
            for(Vertice vertice: vertices){
                Recurso recurso = this.terreno.darRecurso();
                vertice.darRecurso(recurso);
            }
        }
    }

    private Vertice buscarVerticeNumero(int numeroDeVertice){
        for(Vertice vertice: vertices){
            if (vertice.numeroDeVerticeEs(numeroDeVertice)){
                return vertice;
            }
        }
        return null;
    }



    public void ubicarPoblado(Jugador jugador, int numeroDeVertice){
        Vertice verticeDestino = buscarVerticeNumero(numeroDeVertice);
        verticeDestino.ubicarPoblado(jugador);
    }

    public void ubicarCiudad(Jugador jugador, int numeroDeVertice){
        Vertice verticeDestino = buscarVerticeNumero(numeroDeVertice);
        verticeDestino.ubicarCiudad(jugador);
    }

    //Consultar si esto esta bien.
    public void setVertices(Vertice[] vertices) {
        this.vertices = vertices;
    }

    public static List<Hexagono> generar19Hexagonos() {

        List<Hexagono> hexagonos = Factory_MapaBasico.mezclarNumerosYHexagonos();

        for (int i = 0; i < hexagonos.size(); i++) {
            System.out.println(hexagonos.get(i) + " " + i + " " + hexagonos.get(i).numero);
            for (Vertice vertice : hexagonos.get(i).vertices) {
                System.out.println(vertice);
            }
        }
        return hexagonos;

    }

    public boolean esDesierto() {
        return this.numero == 7;
    }

}