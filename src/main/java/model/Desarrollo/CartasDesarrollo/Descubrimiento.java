package model.Desarrollo.CartasDesarrollo;

import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Tablero;

import java.util.List;

public class Descubrimiento extends CartaDesarrollo {

    @Override
    public ActivacionDesarrollo prepararActivacion() {

        return new ActivacionDesarrollo() {

            private Recurso r1, r2;

            public void setRecursos(Recurso a, Recurso b) {
                this.r1 = a;
                this.r2 = b;
            }

            @Override
            public void ejecutar(Jugador jugador, Tablero tablero, List<Jugador> jugadores) {
                jugador.pedirAlBanco(r1, 1);
                jugador.pedirAlBanco(r2, 1);
            }
        };
    }
}