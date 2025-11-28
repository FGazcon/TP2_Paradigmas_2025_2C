package Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import Desarrollo.CartasDesarrollo.CartaDesarrollo;
import Jugador.Jugador;

public class Activable extends EstadoCartaDesarrollo {
    @Override
    public void pasarTurnoDeCompra(CartaDesarrollo cartaDesarrollo) {

    }

    @Override
    public void intentarActivarse(CartaDesarrollo cartaDesarrollo, Jugador jugador) {
        cartaDesarrollo.activar(jugador);
    }
}
