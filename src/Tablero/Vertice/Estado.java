package Tablero.Vertice;

import Tablero.Vertice.Estructura.Ciudad;
import Tablero.Vertice.Estructura.Poblado;

public abstract class Estado {

    public abstract void intentarUbicarEstructura(Ciudad ciudad, Vertice vertice);

    public abstract void intentarUbicarEstructura(Poblado poblado, Vertice vertice);

}