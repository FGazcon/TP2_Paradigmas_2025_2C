package Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import Desarrollo.CartasDesarrollo.CartaDesarrollo;
import Jugador.Jugador;

public abstract class EstadoCartaDesarrollo {

    public abstract void pasarTurnoDeCompra(CartaDesarrollo cartaDesarrollo);

    public abstract void intentarActivarse(CartaDesarrollo cartaDesarrollo, Jugador jugador);
}
