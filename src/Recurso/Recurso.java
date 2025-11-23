package Recurso;

import Tablero.Vertice.Estructura.Estructura;

public interface Recurso{


    String nombre();

    Estructura construir(Recurso recurso);

    Estructura construir(Ladrillo ladrillo);

    Estructura construir(Madera madera);

    Estructura construir(Piedra piedra);

    Estructura construir(Trigo trigo);

    Estructura construir(Oveja oveja);


}

