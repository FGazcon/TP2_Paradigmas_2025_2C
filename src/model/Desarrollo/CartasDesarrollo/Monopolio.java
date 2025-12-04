package model.Desarrollo.CartasDesarrollo;

import model.Catan.Catan;
import model.Desarrollo.*;
import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Tablero;

import java.util.List;

public class Monopolio extends CartaDesarrollo {

    @Override
    public ActivacionDesarrollo prepararActivacion() {

        return new ActivacionDesarrollo() {

            private Recurso recursoElegido;

            public void setRecurso(Recurso recurso) {
                this.recursoElegido = recurso;
            }

            private void aplicarMonopolio(Jugador jugadorActual, List<Jugador> jugadores, Recurso recurso) {

                int total = 0;

                for (Jugador j : jugadores) {

                    if (j == jugadorActual) continue;

                    int cantidad = j.getCantidadDeRecursoEspecifico(recurso);

                    if (cantidad > 0) {
                        j.descartarRecurso(recurso, cantidad);
                        jugadorActual.sumarRecurso(recurso, cantidad);
                        total += cantidad;
                    }
                }

                System.out.println("Monopolio: " + jugadorActual.getNombre() + " obtuvo " + total + " unidades de " + recurso.nombre());
            }

            @Override
            public void ejecutar(Jugador jugador, Tablero tablero, List<Jugador> jugadores) {
                aplicarMonopolio(jugador, jugadores, recursoElegido);
            }
        };
    }
}