package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Errores.VerticeVacio;
import Jugador.Jugador;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;

public class Vacio extends Estado {

    public void intentarUbicarPoblado(Jugador jugador, Vertice vertice) {
        vertice.ocuparse(new Poblado(jugador));
        vertice.bloquearAdyacentes();
    }

    public void intentarUbicarCiudad(Jugador jugador, Vertice vertice) {
        throw new VerticeVacio();
    }

}
