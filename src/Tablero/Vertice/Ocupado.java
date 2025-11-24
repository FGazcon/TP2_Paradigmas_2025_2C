package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Errores.VerticeOcupadoPorAlguienMas;
import Jugador.Jugador;
import Tablero.Vertice.Estructura.Ciudad;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;

public class Ocupado extends Estado{

    @Override
    public void intentarUbicarEstructura(Ciudad ciudad, Vertice vertice) {
        vertice.gestionarCiudad(ciudad);
    }

    @Override
    public void intentarUbicarEstructura(Poblado poblado, Vertice vertice) {
        throw new VerticeNoVacio();
    }
}