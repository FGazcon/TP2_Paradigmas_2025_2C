package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;

import java.util.List;

public class NoHayEstructura extends Estructura{
    @Override
    public void entregarRecursos(Recurso recurso) {

    }

    @Override
    public List<Jugador> anotarDueño(List<Jugador> jugadores) {
        return jugadores;
    }

}