package model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Jugador.Jugador;

public abstract class EstadoCartaDesarrollo {

    public abstract void pasarTurnoDeCompra(CartaDesarrollo cartaDesarrollo);

    public abstract void intentarActivarse(CartaDesarrollo cartaDesarrollo, Jugador jugador);
}
