package model.Tablero.Vertice.Estructura;

import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Vertice.Estado;
import model.Tablero.Vertice.Vertice;

import java.util.List;

public class NoHayEstructura extends Estructura{
    @Override
    public void ubicarseEnVerticeEnEstado(Estado estado, Vertice vertice) {
    }

    @Override

    public void entregarRecursos(Recurso recurso) {
    }

    @Override
    public List<Jugador> anotarDuenio(List<Jugador> jugadores) {
        return jugadores;
    }

    @Override
    public void intentarMejorar(Ciudad estructura, Vertice vertice) {
    }

    @Override
    public void intentarMejorar(Poblado estructura, Vertice vertice) {
    }

}