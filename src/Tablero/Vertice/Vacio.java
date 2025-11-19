package Tablero.Vertice;

import Jugador.Jugador;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;

public class Vacio extends Estado {

    public void intentarUbicarEstructura(Jugador jugador,  Vertice vertice) {
        vertice.ocuparse(new Poblado(jugador));
        vertice.bloquearAdyacentes();
    }

}
