package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Tablero.Vertice.Estructura.Estructura;

public class Bloqueado extends Estado {

    @Override
    public void intentarUbicarEstructura(Estructura estructura, Vertice vertice) {
        throw new VerticeNoVacio();
    }

}
