package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Errores.VerticeVacio;
import Jugador.Jugador;
import Tablero.Vertice.Estructura.Ciudad;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;

public class Vacio extends Estado {

    @Override
    public void intentarUbicarEstructura(Poblado poblado, Vertice vertice) {
        vertice.ocuparse(poblado);
        vertice.bloquearAdyacentes();
    }

    @Override
    public void intentarUbicarEstructura(Ciudad ciudad, Vertice vertice) {
        throw new VerticeVacio();
    }
}