package Recurso;

import Tablero.Vertice.Estructura.Estructura;

public class Ladrillo implements Recurso {
    @Override
    public String nombre(){
        return "Ladrillo";
    }
    @Override
    public Estructura construir(Recurso recurso){
       return recurso.construir(this);
    }
    @Override
    public Estructura construir(Madera madera){
        return null;
        //return new Carretera;
    }
    @Override
    public Estructura construir(Ladrillo ladrillo){
        return null;
        //return new Carretera; podria ser algo para el trade con el banco
    }
    @Override
    public Estructura construir(Piedra piedra){
        return null;
        //return new Carretera; podria ser algo para el trade con el banco
    }
    @Override
    public Estructura construir(Trigo trigo){
        return null;
        //return new Carretera; podria ser algo para el trade con el banco
    }
    @Override
    public Estructura construir(Oveja oveja){
        return null;
        //return new Carretera; podria ser algo para el trade con el banco
    }

}
