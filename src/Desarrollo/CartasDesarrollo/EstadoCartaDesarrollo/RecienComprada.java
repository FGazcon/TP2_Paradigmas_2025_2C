package Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import Desarrollo.CartasDesarrollo.CartaDesarrollo;
import Jugador.Jugador;

public class RecienComprada extends EstadoCartaDesarrollo {

    @Override
    public void pasarTurnoDeCompra(CartaDesarrollo cartaDesarrollo) {
        cartaDesarrollo.setEstado(new Activable());
    }

    @Override
    public void intentarActivarse(CartaDesarrollo cartaDesarrollo, Jugador jugador) {

    }
}
