package model.Tablero.Vertice;

import model.Errores.VerticeNoVacio;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Poblado;

public class Bloqueado extends Estado {

    public void intentarUbicarEstructura(Poblado poblado, Vertice vertice) {
        throw new VerticeNoVacio();
    }

    public void intentarUbicarEstructura(Ciudad ciudad, Vertice vertice) {
        throw new VerticeNoVacio();
    }

}