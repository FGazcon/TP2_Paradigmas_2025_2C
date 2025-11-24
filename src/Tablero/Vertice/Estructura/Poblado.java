package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;
import Tablero.Vertice.Estado;
import Tablero.Vertice.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Poblado extends Estructura{

    @Override
    public void ubicarseEnVerticeEnEstado(Estado estado, Vertice vertice) {
        estado.intentarUbicarEstructura(this, vertice);
    }

    @Override
    public void  entregarRecursos(Recurso recurso) {
        List<Recurso> recursosEntregados = new ArrayList<Recurso>();
        this.jugador.pedirAlBanco(recurso);
        //return recursosEntregados;
    }

    public Poblado(Jugador jugador){
        this.jugador = jugador;
    }

    @Override
    public List<Jugador> anotarDuenio(List<Jugador> jugadores) {
        jugadores.add(this.jugador);
        return jugadores;
    }

}