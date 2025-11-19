package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Jugador.Jugador;
import Tablero.Vertice.Estructura.Estructura;

public class Ocupado extends Estado{

    @Override
    public void intentarUbicarEstructura(Jugador jugador, Vertice vertice) {
        throw new VerticeNoVacio();
    }
}
