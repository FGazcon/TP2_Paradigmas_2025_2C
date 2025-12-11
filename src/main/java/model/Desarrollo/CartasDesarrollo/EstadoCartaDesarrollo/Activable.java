package model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import model.Desarrollo.CartasDesarrollo.ActivacionDesarrollo;
import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;

import java.util.List;

public class Activable extends EstadoCartaDesarrollo {

    @Override
    public ActivacionDesarrollo intentarActivarse(CartaDesarrollo cartaDesarrollo) {
        return cartaDesarrollo.prepararActivacion();
    }

    @Override
    public List<CartaDesarrollo> sumarActivable(List<CartaDesarrollo> cartasDesarrollo, CartaDesarrollo cartaDesarrollo){
        cartasDesarrollo.add(cartaDesarrollo);
        return cartasDesarrollo;
    }

}
