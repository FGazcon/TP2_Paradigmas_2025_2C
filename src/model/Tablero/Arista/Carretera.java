package model.Tablero.Arista;

import model.Jugador.Jugador;

public class Carretera {

    private Jugador jugador;

    public Carretera(Jugador jugador) {
        this.jugador = jugador;
    }


    public Jugador getJugador() {
        return jugador;
    }

    //Ver si podemos sacar esto. Arreglar mecanica entera de chequeo de adyacencia.
    public boolean perteneceA(Jugador jugador) {
        return this.jugador == jugador;
    }
}
