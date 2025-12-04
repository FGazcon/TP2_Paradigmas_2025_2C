package model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import model.Desarrollo.CartasDesarrollo.ActivacionDesarrollo;
import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Jugador.Jugador;

import java.util.List;

public abstract class EstadoCartaDesarrollo {

    public void pasarTurnoDeCompra(CartaDesarrollo cartaDesarrollo){}

    public ActivacionDesarrollo intentarActivarse(CartaDesarrollo cartaDesarrollo){
        return null;
    }

    public List<CartaDesarrollo> sumarActivable(List<CartaDesarrollo> cartasDesarrollo, CartaDesarrollo cartaDesarrollo){
        return cartasDesarrollo;
    }

    public List<CartaDesarrollo> sumarUsada(List<CartaDesarrollo> cartasDesarrollo, CartaDesarrollo cartaDesarrollo){
        return cartasDesarrollo;
    }

    public List<CartaDesarrollo> sumarRecienComprada(List<CartaDesarrollo> cartasDesarrollo, CartaDesarrollo cartaDesarrollo){
        return cartasDesarrollo;
    }
}
