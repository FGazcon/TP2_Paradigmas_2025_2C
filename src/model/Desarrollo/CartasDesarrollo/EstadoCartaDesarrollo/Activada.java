package model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Jugador.Jugador;

import java.util.List;

public class Activada extends EstadoCartaDesarrollo{

    @Override
    public List<CartaDesarrollo> sumarUsada(List<CartaDesarrollo> cartasDesarrollo, CartaDesarrollo cartaDesarrollo){
        cartasDesarrollo.add(cartaDesarrollo);
        return cartasDesarrollo;
    }
}
