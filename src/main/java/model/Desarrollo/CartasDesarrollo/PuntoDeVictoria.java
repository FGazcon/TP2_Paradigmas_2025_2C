package model.Desarrollo.CartasDesarrollo;

public class PuntoDeVictoria extends CartaDesarrollo {

    @Override
    public ActivacionDesarrollo prepararActivacion() {
        return (jugador, tablero, jugadores) -> jugador.sumarPunto();
    }
}