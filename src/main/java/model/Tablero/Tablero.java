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

    private List<Hexagono> hexagonos;
    private Ladron ladron;
    private List<Vertice> verticesUnicos;

    public Tablero(List<Hexagono> hexagonos) {
        this.hexagonos = hexagonos;
        this.ladron = new Ladron(buscarDesierto());
    }

    public static Tablero crearTableroBasico(){
        List<Hexagono> hexagonos = Factory_MapaBasico.crearHexagonosBasico();
        return new Tablero(hexagonos);
    }

    public Hexagono buscarDesierto() {
        for (int i = 0; i < this.hexagonos.size(); i++) {
            if(hexagonos.get(i).esDesierto()){
                return hexagonos.get(i);
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

    public void moverLadron(int nuevoHexagono, Jugador jugador){
        ladron.moverLadron(this.hexagonos.get(nuevoHexagono), jugador);
    }

    public void ubicarCarretera(Carretera carretera, int[] numeroDeArista) {
        Hexagono hexagonoOrigen = buscarHexagonoConVertice(numeroDeArista[0]);

        hexagonoOrigen.ubicarCarretera(carretera, numeroDeArista);
    }

    public List<Hexagono> getHexagonos() {
        return this.hexagonos;
    }

    public List<Vertice> getVerticesUnicos() {
        if (verticesUnicos == null) {
            Set<Vertice> set = new HashSet<>();
            for (Hexagono h : this.hexagonos) {
                set.addAll(Arrays.asList(h.getVertices()));
            }
            verticesUnicos = new ArrayList<>(set);
        }
        return verticesUnicos;
    }

    public List<Arista> getAristasUnicas() {
        Set<Arista> unicas = new HashSet<>();

        for (Vertice v : getVerticesUnicos()) {
            for (Arista a : v.getAristas()) {
                unicas.add(a);
            }
        }

        return new ArrayList<>(unicas);
    }


}