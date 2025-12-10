package model.Desarrollo.CartasDesarrollo;

import model.Jugador.Jugador;
import model.Tablero.Hexagono;
import model.Tablero.Tablero;

import java.util.List;

public class Caballero extends CartaDesarrollo {

    @Override
    public ActivacionDesarrollo prepararActivacion() {

        return new ActivacionDesarrollo() {

            private Hexagono hexElegido;

            public void setHex(Hexagono hex) {this.hexElegido = hex;}

            @Override
            public void ejecutar(Jugador jugador, Tablero tablero, List <Jugador> jugadores) {
                tablero.moverLadron(hexElegido, jugador);
            }
        };
    }
}
