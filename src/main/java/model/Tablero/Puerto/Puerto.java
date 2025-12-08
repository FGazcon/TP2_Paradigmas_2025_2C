package model.Tablero.Puerto;

import model.Recurso.Recurso;
import model.Jugador.Jugador;

public abstract class Puerto {
    protected Recurso recurso;
    //public abstract ReglaDeComercio regla();

    public abstract void darReglaAJugador(Jugador jugador);
}
