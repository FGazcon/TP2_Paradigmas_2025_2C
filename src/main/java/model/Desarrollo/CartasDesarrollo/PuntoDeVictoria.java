package model.Desarrollo.CartasDesarrollo;

public class PuntoDeVictoria extends CartaDesarrollo {

    @Override
    public int modificarPuntaje(int puntaje){
        return puntaje + 1;
    }

    @Override
    public ActivacionDesarrollo prepararActivacion() {
        return (jugador, tablero, jugadores) -> jugador.sumarPunto();
    }
}