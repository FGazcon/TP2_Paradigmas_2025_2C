package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;

public abstract class Estructura {

    protected Jugador jugador;

    public abstract void entregarRecursos(Recurso recurso);

    public static Poblado nuevoPoblado(Jugador jugador){
        return new Poblado(jugador);
    }

}
