package Tablero.Vertice.Estructura;

import Jugador.Jugador;
import Recurso.Recurso;

public class Poblado extends Estructura{

    @Override
    public void entregarRecursos(Recurso recurso) {
        this.jugador.pedirAlBanco(recurso);
    }

    public Poblado(Jugador jugador){
        this.jugador = jugador;
    }

}