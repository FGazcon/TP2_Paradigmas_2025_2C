package model.Tablero.Arista;

import model.Errores.AristaFueraDeAlcance;
import model.Jugador.Jugador;
import model.Tablero.Vertice.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Arista {

    private Vertice destino;
    private int[] numeroDeVertices;
    private Carretera carretera;
    private EstadoArista estadoArista;
    private Arista par;

    public Arista(Vertice destino, int[] numeroDeVertices) {
        this.destino = destino;
        this.numeroDeVertices = numeroDeVertices;
        this.carretera = null;
        this.estadoArista = new AristaVacia();
    }

    public void bloquearDestino(){
        this.destino.bloquearse();
    }

    public void ocuparse(Carretera carretera) {
        this.carretera = carretera;
        this.estadoArista = new AristaOcupada();
    }

    public void ocuparPar(Carretera carretera) {
        this.par.ocuparse(carretera);
    }

    public int iniciarConteoCamino(){

        if (this.carretera == null) {
            return 0;
        }

        Jugador jugador = this.carretera.getJugador();

        Vertice vertice1 = this.par.getDestino();
        Vertice vertice2 = this.destino;

        List<Arista> aristasVisitadas = new ArrayList<>();

        aristasVisitadas.add(this);
        aristasVisitadas.add(this.par);

        int largoDesdeV1 = vertice1.getLongitudMaximaDeCamino(jugador, aristasVisitadas);

        aristasVisitadas.clear();
        aristasVisitadas.add(this);
        aristasVisitadas.add(this.par);

        int largoDesdeV2 = vertice2.getLongitudMaximaDeCamino(jugador, aristasVisitadas);

        return 1 + largoDesdeV1 + largoDesdeV2;
    }

    //De aca para abajo hay que buscar un refactor, esta bastante mal la cantidad de cosas que pedimos.

    public void setPar(Arista par){
        this.par = par;
    }

    public boolean validarCarreteraPara(Jugador jugador) {
        return aristaValidaPara(jugador) | this.par.aristaValidaPara(jugador);
    }

    public boolean aristaValidaPara(Jugador jugador) {
        return destino.permiteConstruccionDeAristaDe(jugador);
    }

    public boolean esDe(Jugador jugador) {
        return estadoArista.esDe(jugador, this.carretera);
    }

    public void ubicarCarretera(Carretera carretera, int[] numeroDeArista) {

        if(this.destino.numeroDeVerticeEs(numeroDeArista[1])){
            if(aristaValidaPara(carretera.getJugador()) | par.aristaValidaPara(carretera.getJugador())) {
                this.estadoArista.ubicarCarretera(carretera, this);
            } else {
                throw new AristaFueraDeAlcance();
            }
        }
    }

    public Vertice getDestino() {
        return destino;
    }

    public Arista getPar() {
        return par;
    }

    public int[] getNumeroDeVertices() {
        return numeroDeVertices;
    }

    ///getters interfaz

    public Carretera getCarretera(){
        return this.carretera;
    }
}
