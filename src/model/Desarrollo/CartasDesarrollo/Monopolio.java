package model.Desarrollo.CartasDesarrollo;

import model.Jugador.Jugador;

import java.util.List;

public class Monopolio extends CartaDesarrollo{

    //Debera tener acceso a todos los otros jugadores. Relacionarlos entre si.

    @Override
    public void activar(Jugador jugador) {

    }

    public void robarCartaATodosLosJugadores(List<Jugador> jugadoresRobados,Jugador jugadorQueRoba){
        for(Jugador jugador : jugadoresRobados){
            jugador.dejarseRobarPorJugador(jugadorQueRoba);
        }
    }


}
