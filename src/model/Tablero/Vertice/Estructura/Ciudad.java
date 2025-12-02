package model.Tablero.Vertice.Estructura;

import model.Errores.VerticeOcupadoPorCiudad;
import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Vertice.Estado;
import model.Tablero.Vertice.Vertice;

import java.util.List;

public class Ciudad extends Estructura {

    @Override
    public void ubicarseEnVerticeEnEstado(Estado estado, Vertice vertice) {
        estado.intentarUbicarEstructura(this, vertice);
    }

    @Override
    public void entregarRecursos(Recurso recurso) {
        recurso.hacerQuejugadorSoliciteABanco(this.jugador, 2);
    }

    @Override
    public List<Jugador> anotarDuenio(List<Jugador> jugadores) {
        jugadores.add(this.jugador);
        return jugadores;
    }

    @Override
    public void intentarMejorar(Ciudad estructura, Vertice vertice) {
        throw new VerticeOcupadoPorCiudad();
    }

    @Override
    public void intentarMejorar(Poblado estructura, Vertice vertice) {
        throw new VerticeOcupadoPorCiudad();
    }

    public Ciudad(Jugador jugador) {
        this.jugador = jugador;
    }
}