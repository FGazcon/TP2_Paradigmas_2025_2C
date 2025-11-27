package Tablero;

import Jugador.Jugador;
import Ladron.Ladron;
import Tablero.Arista.Carretera;
import Tablero.Factory.Factory_MapaBasico;
import Tablero.Vertice.Estructura.Estructura;

import java.util.List;

public class Tablero {

    private List<Hexagono> hexagonos;
    private Ladron ladron;

    public Tablero(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;
    }

    public static Tablero crearTableroBasico(){
        List<Hexagono> hexagonos = Factory_MapaBasico.crearHexagonosBasico();
        return new Tablero(hexagonos);
    }

    public int buscarDesierto() {
        for (int i = 0; i < this.hexagonos.size(); i++) {
            if(hexagonos.get(i).esDesierto()){
                return i;
            }
        }
        return 0;
    }

    public void activarHexagonoPorNumero(int numero){
        for(Hexagono hexagono: hexagonos){
            hexagono.activarHexagonoParaNumero(numero);
        }
    }

    public void activarHexagonoPorVertice(int vertice_segundo_poblado) {
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

    public void ubicarEstructura(Estructura estructura, int numeroDeVertice) {
        Hexagono hexagonoConVertice = buscarHexagonoConVertice(numeroDeVertice);

        hexagonoConVertice.ubicarEstructura(estructura, numeroDeVertice);
    }

    public void moverLadron(int nuevoHexagono, Jugador jugador){
        ladron.moverLadron(this.hexagonos.get(nuevoHexagono), jugador);
    }

    public void ubicarCarretera(Carretera carretera, int numeroDeArista) {
        for (Hexagono hexagono : hexagonos) {

        }
    }
}