package Tablero.Vertice;

import Errores.VerticeNoVacio;
import Tablero.Vertice.Estructura.Estructura;

public class Ocupado extends Estado{

    @Override
    public void intentarUbicarEstructura(Estructura estructura, Vertice vertice) {
        throw new VerticeNoVacio();
    }
}
