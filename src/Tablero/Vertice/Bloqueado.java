package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Jugador.Jugador;
import Tablero.Vertice.Estructura.Estructura;

public class Bloqueado extends Estado {

    @Override
    public void intentarUbicarPoblado(Jugador jugador, Vertice vertice) {
        throw new VerticeNoVacio();
    }

    public void intentarUbicarCiudad(Jugador jugador, Vertice vertice) {
        throw new VerticeNoVacio();
    }

}
