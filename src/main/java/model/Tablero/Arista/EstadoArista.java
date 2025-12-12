package model.Tablero.Arista;

import model.Jugador.Jugador;

public abstract class EstadoArista {
    public abstract void ubicarCarretera(Carretera carretera, Arista arista);

    public abstract boolean esDe(Jugador jugador, Carretera carretera);
}
