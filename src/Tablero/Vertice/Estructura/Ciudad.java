package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;

import java.util.List;

public class Ciudad extends Estructura {

    @Override
    public void entregarRecursos(Recurso recurso) {
        this.jugador.pedirAlBanco(recurso);
        this.jugador.pedirAlBanco(recurso);
    }

    @Override
    public List<Jugador> anotarDueño(List<Jugador> jugadores) {
        jugadores.add(this.jugador);
        return jugadores;
    }

    public Ciudad(Jugador jugador) {
        this.jugador = jugador;
    }
}