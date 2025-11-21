package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;

import java.util.List;

public class Poblado extends Estructura{

    @Override
    public void entregarRecursos(Recurso recurso) {
        this.jugador.pedirAlBanco(recurso);
    }

    public Poblado(Jugador jugador){
        this.jugador = jugador;
    }

    @Override
    public List<Jugador> anotarDueño(List<Jugador> jugadores) {
        jugadores.add(this.jugador);
        return jugadores;
    }

}