package Comercio;

import Jugador.Jugador;
import Recurso.Recurso;

public class CuatroPorUno implements ReglaDeComercio {

    @Override
    public boolean puedeHacerComercioMaritimo(Jugador jugador, Recurso ofrecido) {
        return jugador.tieneAlMenos(ofrecido.nombre(), 4);
    }

    @Override
    public void realizarComercioMaritimo(Jugador jugador, Recurso ofrecido, Recurso pedido) {
        jugador.descartarRecurso(ofrecido.nombre(), 4);
        jugador.sumarRecurso(pedido.nombre(), 1);
    }
}