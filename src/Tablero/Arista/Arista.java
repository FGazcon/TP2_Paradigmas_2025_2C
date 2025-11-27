package Tablero.Arista;

import Errores.AristaFueraDeAlcance;
import Jugador.Jugador;
import Tablero.Vertice.Vertice;

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
}
