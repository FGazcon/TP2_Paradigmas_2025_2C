package model.Desarrollo.CartasDesarrollo;

import model.Catan.Catan;
import model.Desarrollo.*;
import model.Jugador.Jugador;
import model.Tablero.Arista.Carretera;
import model.Tablero.Tablero;

import java.util.List;

public class ConstruccionDeCarreteras extends CartaDesarrollo {

    @Override
    public ActivacionDesarrollo prepararActivacion() {

        return new ActivacionDesarrollo() {

            private int[] arista1;
            private int[] arista2;

            public void setAristas(int[] a1, int[] a2) {
                this.arista1 = a1;
                this.arista2 = a2;
            }

            @Override
            public void ejecutar(Jugador jugador, Tablero tablero, List<Jugador> jugadores) {
                tablero.ubicarCarretera(new Carretera(jugador), arista1);
                tablero.ubicarCarretera(new Carretera(jugador), arista2);
            }
        };
    }
}