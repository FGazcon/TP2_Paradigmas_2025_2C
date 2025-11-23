package Recurso;

import Tablero.Vertice.Estructura.Estructura;

public class Oveja implements Recurso {

    @Override
    public String nombre(){
        return "Oveja";
    }

    @Override
    public Estructura construir(Madera madera) {
        return null;
    }

    @Override
    public Estructura construir(Oveja oveja) {
        return null;
    }

    @Override
    public Estructura construir(Trigo trigo) {
        return null;
    }

    @Override
    public Estructura construir(Piedra piedra) {
        return null;
    }

    @Override
    public Estructura construir(Ladrillo ladrillo) {
        return null;
    }

    @Override
    public Estructura construir(Recurso recurso) {
        return recurso.construir(this);
    }
}
