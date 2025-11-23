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
        @Override
        public Estructura construirPoblado(Recurso recurso1,Recurso recurso2,Recurso recurso3){
                return recurso1.construirPoblado(this,recurso2,recurso3);
                // aca hay que devolver un poblado pero el poblado hay que mandarle un jugador, ta raro return new Poblado();
        }
        @Override
        public Estructura construir(Madera madera, Ladrillo ladrillo, Oveja oveja, Trigo trigo){
                return null;
                // aca hay que devolver un poblado pero el poblado hay que mandarle un jugador, ta raro return new Poblado();
        }
        @Override
        public Estructura construir(Piedra piedra, Oveja oveja, Trigo trigo){
                return null;
                //return new CartaDesarrollo();
        }
}
