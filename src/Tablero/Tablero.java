package Tablero;

import Jugador.Jugador;
import Ladron.Ladron;

import java.util.List;

public class Tablero {

    private List<Hexagono> hexagonos;
    private Ladron ladron;

    public Tablero() {

        this.hexagonos = Hexagono.generar19Hexagonos();
        this.ladron = new Ladron(buscarDesierto());
    }

    public void activarHexagono(int numero){
        for(Hexagono hexagono: hexagonos){
            hexagono.activarHexagonoNumero(numero);
        }
    }

    public void activarHexagonoParaSegundoPoblado(int vertice_segundo_poblado) {
        for (Hexagono hexagono : hexagonos) {
            hexagono.activarHexagonoParaVerticeEspecifico(vertice_segundo_poblado);
        }
    }

    private Hexagono buscarHexagonoConVertice(int numeroDeVerice) {

        for (Hexagono hexagono : hexagonos) {
            if(hexagono.contieneVertice(numeroDeVerice)){
                return hexagono;
            }
        }
        return null;
    }

    public int ubicarPoblado(Jugador jugador, int numeroDeVerice) {

        Hexagono hexagonoConVertice = buscarHexagonoConVertice(numeroDeVerice);

        hexagonoConVertice.ubicarPoblado(jugador, numeroDeVerice);

        return numeroDeVerice;

    }

    public int buscarDesierto() {
        for (int i = 0; i < hexagonos.size(); i++) {
            if (hexagonos.get(i).esDesierto()){
                return i;
            }
        }
        return 0;
    }

}
