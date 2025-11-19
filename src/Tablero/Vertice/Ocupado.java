package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Errores.VerticeOcupadoPorAlguienMas;
import Jugador.Jugador;
import Tablero.Vertice.Estructura.Ciudad;
import Tablero.Vertice.Estructura.Estructura;

public class Ocupado extends Estado{

    @Override
    public void intentarUbicarPoblado(Jugador jugador, Vertice vertice) {
        throw new VerticeNoVacio();
    }

    @Override
    public void intentarUbicarCiudad(Jugador jugador, Vertice vertice) {
        if(vertice.estructuraEsDe(jugador)){
            vertice.ocuparse(new Ciudad(jugador));
        } else {
            throw new VerticeOcupadoPorAlguienMas();
        }
    }
}
