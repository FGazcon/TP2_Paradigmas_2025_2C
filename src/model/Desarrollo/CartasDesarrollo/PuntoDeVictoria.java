package model.Desarrollo.CartasDesarrollo;

import model.Jugador.Jugador;

public class PuntoDeVictoria extends CartaDesarrollo{

    //Debera tener acceso al conteo de puntos
    //Podria no implementarse, y directamente sumar cuando se saque del Banco.

    @Override
    public void activar(Jugador jugador) {

    }

    @Override
    public int modificarPuntaje(int puntaje){
        return puntaje + 1;
    }


}
