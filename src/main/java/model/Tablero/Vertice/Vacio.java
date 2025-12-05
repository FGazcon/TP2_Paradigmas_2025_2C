package model.Tablero.Vertice;

import model.Errores.VerticeVacio;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Poblado;

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