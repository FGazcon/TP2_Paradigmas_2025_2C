package Recurso;

import Tablero.Vertice.Estructura.Estructura;

public class Madera implements Recurso {

        @Override
        public String nombre(){
                return "Madera";
        }
        @Override
        public Estructura construir(Recurso recurso){
                return recurso.construir(this);
        }
        @Override
        public Estructura construir(Ladrillo ladrillo){
                return null;
                //return new Carretera;
        }
        @Override
        public Estructura construir(Madera madera){
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

}
