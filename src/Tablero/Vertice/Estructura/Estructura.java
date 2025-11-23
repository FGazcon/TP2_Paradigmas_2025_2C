package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;

import java.util.List;

public abstract class Estructura {

    protected Jugador jugador;

    public abstract void entregarRecursos(Recurso recurso);

    public abstract List<Jugador> anotarDuenio(List<Jugador> jugadores);

}
