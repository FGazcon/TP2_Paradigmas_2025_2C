package model.Desarrollo.CartasDesarrollo;

import model.Jugador.Jugador;
import model.Tablero.Tablero;

import java.util.List;

public interface ActivacionDesarrollo {
    void ejecutar(Jugador jugador, Tablero tablero, List <Jugador> jugadores);
}
