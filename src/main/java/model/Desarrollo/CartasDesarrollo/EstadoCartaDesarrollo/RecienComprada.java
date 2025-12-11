package model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo;

import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;

import java.util.List;

public class RecienComprada extends EstadoCartaDesarrollo {

    @Override
    public void pasarTurnoDeCompra(CartaDesarrollo cartaDesarrollo) {
        cartaDesarrollo.setEstado(new Activable());
    }

    public List<CartaDesarrollo> sumarRecienComprada(List<CartaDesarrollo> cartasDesarrollo, CartaDesarrollo cartaDesarrollo){
        cartasDesarrollo.add(cartaDesarrollo);
        return cartasDesarrollo;
    }

}
