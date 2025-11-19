package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Produccion.Recurso;

public abstract class Estructura {

    protected Jugador jugador;

    public abstract void entregarRecursos(Recurso recurso);

    public static Poblado nuevoPoblado(Jugador jugador){
        return new Poblado(jugador);
    }

    public boolean esDe(Jugador jugador){
        return this.jugador.equals(jugador);
    }

}
