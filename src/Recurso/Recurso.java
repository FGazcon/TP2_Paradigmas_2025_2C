package Recurso;

import Tablero.Vertice.Estructura.Estructura;

public interface Recurso{

    //public abstract void usar();

    public abstract String nombre();
    public abstract Estructura construir(Recurso recurso);
    //Estructura construir(Recurso recurso1, Recurso recurso2);
    public abstract Estructura construir(Ladrillo ladrillo);
    public abstract Estructura construir(Madera madera);

    Estructura construir(Piedra piedra);

    Estructura construir(Trigo trigo);

    Estructura construir(Oveja oveja);

    Estructura construirPoblado(Recurso recurso1, Recurso recurso2, Recurso recurso3);

    Estructura construir(Madera madera, Ladrillo ladrillo, Oveja oveja, Trigo trigo);

    Estructura construir(Piedra piedra, Oveja oveja, Trigo trigo);
}

