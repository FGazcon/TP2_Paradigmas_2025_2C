package model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Jugador.Jugador;

public class Activable extends EstadoCartaDesarrollo {
    @Override
    public void pasarTurnoDeCompra(CartaDesarrollo cartaDesarrollo) {

    }

    @Override
    public void intentarActivarse(CartaDesarrollo cartaDesarrollo, Jugador jugador) {
        cartaDesarrollo.activar(jugador);
    }
}
