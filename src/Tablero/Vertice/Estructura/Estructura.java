package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;
import Tablero.Vertice.Estado;
import Tablero.Vertice.Vertice;

import java.util.List;

public abstract class Estructura {

    protected Jugador jugador;

    public abstract void ubicarseEnVerticeEnEstado(Estado estado, Vertice vertice);

    public abstract void entregarRecursos(Recurso recurso);

    public abstract List<Jugador> anotarDuenio(List<Jugador> jugadores);



    public boolean esDe(Jugador jugador) {
        //ESTAM FUNDAMENTALMENTE MAL, CONSIDERAR COMO LO PODEMOS CAMBIAR
        return jugador ==  this.jugador;
    }
    public Jugador getJugador() {
        return this.jugador;
    }
}
