package Tablero;

import Errores.VerticeNoVacio;
import Jugador.Jugador;
import Ladron.Ladron;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;
import Tablero.Vertice.Vertice;

import java.util.List;
import java.util.Scanner;

public class Tablero {

    private List<Hexagono> hexagonos;
    private Ladron ladron;




    public Tablero() {

        this.hexagonos = Hexagono.generar19Hexagonos();
        this.ladron = new Ladron(buscarDesierto());
    }

    public void activarHexagono(int numero){
        for (int i = 0; i < hexagonos.size(); i++) {
            hexagonos.get(i).activarHexagonoNumero(numero);
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

    public void activarParaSegundoPoblado(int vertice_segundo_poblado) {
        for (Hexagono hexagono : hexagonos) {
            hexagono.activarHexagonoParaVerticeEspecifico(vertice_segundo_poblado);
        }
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
