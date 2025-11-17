package Tablero;

import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Vertice;

import java.util.List;

public class Tablero {

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

}
