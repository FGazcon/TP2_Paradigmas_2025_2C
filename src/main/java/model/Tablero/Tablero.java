package model.Tablero;

import model.Jugador.Jugador;
import model.Ladron.Ladron;
import model.Tablero.Arista.Arista;
import model.Tablero.Arista.Carretera;
import model.Tablero.Factory.Factory_MapaBasico;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Vertice;

import java.util.*;

public class Tablero {

    private final List<Hexagono> hexagonos;
    private final Ladron ladron;

    public Tablero(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;
        this.ladron = new Ladron(buscarDesierto());
    }

    public static Tablero crearTableroBasico(){
        List<Hexagono> hexagonos = Factory_MapaBasico.crearHexagonosBasico();
        return new Tablero(hexagonos);
    }

    public Hexagono buscarDesierto() {
        for (Hexagono hexagono : this.hexagonos) {
            if (hexagono.esDesierto()) {
                return hexagono;
            }
        }
        return null;
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

    public void moverLadron(Hexagono hexagono, Jugador jugador){

        ladron.moverLadron(hexagono, jugador);

    }

    public void ubicarCarretera(Carretera carretera, int[] numeroDeArista) {
        Hexagono hexagonoOrigen = buscarHexagonoConVertice(numeroDeArista[0]);

        hexagonoOrigen.ubicarCarretera(carretera, numeroDeArista);
    }

    public List<Hexagono> getHexagonos() {
        return this.hexagonos;
    }

    public List<Vertice> getVerticesUnicos() {
        Set<Vertice> set = new HashSet<>();
        for (Hexagono h : this.hexagonos) {
            set.addAll(Arrays.asList(h.getVertices()));
        }
        return new ArrayList<>(set);
    }

    public List<Arista> getAristasUnicas() {
        Set<Arista> unicas = new HashSet<>();

        for (Vertice v : getVerticesUnicos()) {
            unicas.addAll(v.getAristas());
        }

        return new ArrayList<>(unicas);
    }
    /////getters para la app

}