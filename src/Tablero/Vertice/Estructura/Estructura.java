package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Produccion.Recurso;

public abstract class Estructura {

    protected Jugador jugador;

    public abstract void entregarRecursos(Recurso recurso);

}
