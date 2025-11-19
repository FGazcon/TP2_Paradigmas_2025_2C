package Tablero;

import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Vertice;
import Ladron.Ladron;

import java.util.List;

public class Tablero {
    private Vertice[] vertices;
    private List<Hexagono> hexagonos;

    public Tablero() {

        this.hexagonos = Hexagono.generar19Hexagonos();
    }

    public void ubicarEstructura(Estructura estructura, int numeroDeVerice) {

        Hexagono hexagonoConVertice = buscarHexagonoConVertice(numeroDeVerice);

        hexagonoConVertice.ubicarEstructura(estructura, numeroDeVerice);

    }


    private Hexagono buscarHexagonoConVertice(int numeroDeVerice) {

        for (Hexagono hexagono : hexagonos) {
            if(hexagono.contieneVertice(numeroDeVerice)){
                return hexagono;
            }
        }
        return null;
    }

    public void activarParaSegundoPoblado(int[] vertices_segundo_poblado) {
        for (Hexagono hexagono : hexagonos) {
            hexagono.activarseParaVerticeEspecifico(vertices_segundo_poblado);
        }
    }

    ///// aca esta lo que cree yo


    public void darRecursosHexagonosAdyacentesAlVertice(int vertice) {
        for (Hexagono hexagono : hexagonos) {
            hexagono.darRecursoAlVertice(vertice);
        }
    }
    private Hexagono buscarHexagonoPorNumero(int numeroHexagono){
        for(Hexagono hexagono : this.hexagonos){
            if(hexagono.hexagonoCorrecto(numeroHexagono)){
                return hexagono;
            }
        }
        return null;
    }
    public void activarHexagono(int numeroHexagono){
        Hexagono hexagono;

        hexagono = buscarHexagonoPorNumero(numeroHexagono);
        hexagono.otorgarRecursosVertices();
    }

    public void moverLadron(Ladron ladron,int numeroHexagono){

       // ladron.moverLadron(hexagono);
    }

}
