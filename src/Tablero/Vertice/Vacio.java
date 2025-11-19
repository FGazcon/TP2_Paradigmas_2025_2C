package Tablero.Vertice;

import Tablero.Vertice.Estructura.Estructura;

public class Vacio extends Estado {

    public void intentarUbicarEstructura(Estructura estructura,  Vertice vertice) {
        vertice.ocuparse(estructura);
        vertice.bloquearAdyacentes();
    }

}
