package Tablero.Vertice;

import Jugador.Jugador;

public abstract class Estado {

    public abstract void intentarUbicarPoblado(Jugador jugador, Vertice vertice);

    public abstract void intentarUbicarCiudad(Jugador jugador, Vertice vertice);
}