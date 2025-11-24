package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;
import Tablero.Vertice.Estado;
import Tablero.Vertice.Vertice;

import java.util.List;

public class Ciudad extends Estructura {

    @Override
    public void ubicarseEnVerticeEnEstado(Estado estado, Vertice vertice) {
        estado.intentarUbicarEstructura(this, vertice);
    }

    @Override
    public void entregarRecursos(Recurso recurso) {
        this.jugador.pedirAlBanco(recurso);
        this.jugador.pedirAlBanco(recurso);
    }

    @Override
    public List<Jugador> anotarDuenio(List<Jugador> jugadores) {
        jugadores.add(this.jugador);
        return jugadores;
    }

    public Ciudad(Jugador jugador) {
        this.jugador = jugador;
    }
}