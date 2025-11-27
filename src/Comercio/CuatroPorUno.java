package Comercio;

import Jugador.Jugador;
import Recurso.Recurso;

public class CuatroPorUno implements ReglaDeComercio {

    @Override
    public boolean puedeHacerComercioMaritimo(Jugador jugador, Recurso ofrecido) {
        return jugador.tieneCantidadDe(ofrecido, 4);
    }

    @Override
    public void realizarComercioMaritimo(Jugador jugador, Recurso ofrecido, Recurso pedido) {
        jugador.entregarRecurso(ofrecido, 4);
        jugador.recibirRecursoDesdeBanco(pedido);
    }
}