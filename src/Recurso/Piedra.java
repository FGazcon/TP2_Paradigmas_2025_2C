package Recurso;

import Jugador.Jugador;

import java.util.List;

public class Piedra extends Recurso {
        @Override
        public String nombre(){
                return "Piedra";
        }

        @Override
        public void construirPoblado(List<Recurso> recursos,Jugador jugador) {
        }
        @Override
        public void construirCarretera(List<Recurso> recursos,Jugador jugador) {

        }

}

