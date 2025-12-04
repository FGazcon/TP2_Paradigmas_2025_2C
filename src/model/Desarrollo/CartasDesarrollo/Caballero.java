package model.Desarrollo.CartasDesarrollo;

import model.Catan.Catan;
import model.Desarrollo.*;
import model.Jugador.Jugador;
import model.Tablero.Tablero;

import java.util.List;

public class Caballero extends CartaDesarrollo {

    @Override
    public ActivacionDesarrollo prepararActivacion() {

        return new ActivacionDesarrollo() {

            private int hexElegido;

            public void setHex(int hex) {
                this.hexElegido = hex;
            }

            @Override
            public void ejecutar(Jugador jugador, Tablero tablero, List <Jugador> jugadores) {
                tablero.moverLadron(hexElegido, jugador);
            }
        };
    }
}
