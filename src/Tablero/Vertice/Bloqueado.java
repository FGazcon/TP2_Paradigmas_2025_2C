package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Jugador.Jugador;
import Tablero.Vertice.Estructura.Ciudad;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;

public class Bloqueado extends Estado {

    public void intentarUbicarEstructura(Poblado poblado, Vertice vertice) {
        throw new VerticeNoVacio();
    }

    public void intentarUbicarEstructura(Ciudad ciudad, Vertice vertice) {
        throw new VerticeNoVacio();
    }

}