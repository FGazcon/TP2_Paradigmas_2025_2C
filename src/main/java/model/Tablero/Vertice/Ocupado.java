package model.Tablero.Vertice;

import model.Errores.VerticeNoVacio;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Poblado;

public class Ocupado extends Estado{

    @Override
    public void intentarUbicarEstructura(Ciudad ciudad, Vertice vertice) {
        vertice.intentarUbicarCiudad(ciudad);
    }

    @Override
    public void intentarUbicarEstructura(Poblado poblado, Vertice vertice) {
        throw new VerticeNoVacio();
    }
}