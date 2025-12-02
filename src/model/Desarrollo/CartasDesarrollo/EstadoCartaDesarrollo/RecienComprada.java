package model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Jugador.Jugador;

public class RecienComprada extends EstadoCartaDesarrollo {

    @Override
    public void pasarTurnoDeCompra(CartaDesarrollo cartaDesarrollo) {
        cartaDesarrollo.setEstado(new Activable());
    }

    @Override
    public void intentarActivarse(CartaDesarrollo cartaDesarrollo, Jugador jugador) {

    }
}
