package Recurso;

import Tablero.Vertice.Estructura.Estructura;

public class Trigo implements Recurso {

        @Override
        public String nombre(){
                return "Trigo";
        }

        @Override
        public Estructura construir(Madera madera) {
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
