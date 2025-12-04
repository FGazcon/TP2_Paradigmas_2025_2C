package model.Desarrollo.CartasDesarrollo;

import model.Desarrollo.*;

public class PuntoDeVictoria extends CartaDesarrollo {

    @Override
    public ActivacionDesarrollo prepararActivacion() {
        return (jugador, tablero, jugadores) -> jugador.sumarPunto();
    }
}