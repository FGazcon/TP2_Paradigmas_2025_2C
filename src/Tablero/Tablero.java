package Tablero;

import Ladron.Ladron;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

import java.util.List;

public class Tablero {
    private Vertice[] vertices;
    private List<Hexagono> hexagonos;

    public Tablero() {

        this.hexagonos = Hexagono.generar19Hexagonos(Terreno.generar19Terrenos());
    }
    public Tablero(List<Hexagono> hexagonosAColocar) {

        this.hexagonos = hexagonosAColocar;
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

    public void ubicarPoblado(int posicionPoblado){
        Hexagono hexagonoConVertice = buscarHexagonoConVertice(posicionPoblado);

       // hexagonoConVertice.ubicarEstructura(new Estructura(),posicionPoblado);
    }
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
        assert hexagono != null;
        //hexagono.otorgarRecursosVertices();
    }

    public void moverLadron(Ladron ladron,int numeroHexagono){

       // ladron.moverLadron(hexagono);
    }

}
