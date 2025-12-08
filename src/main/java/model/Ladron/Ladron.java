package model.Ladron;

import model.Jugador.Jugador;
import model.Tablero.Hexagono;

public class Ladron {

    private Hexagono hexagonoBajoAtaque;

    public Ladron(Hexagono hexagonoBajoAtaque){
        this.hexagonoBajoAtaque = hexagonoBajoAtaque;
    }

    public void moverLadron(Hexagono nuevoHexagono, Jugador jugadorQueMovio){
        this.hexagonoBajoAtaque.liberarse();
        this.hexagonoBajoAtaque = nuevoHexagono;
        this.hexagonoBajoAtaque.recibirLadron(jugadorQueMovio);
    }

    public Hexagono getHexagonoBajoAtaque() {
        return this.hexagonoBajoAtaque;
    }
}
