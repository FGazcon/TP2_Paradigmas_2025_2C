package Comercio;

import Jugador.Jugador;
import Recurso.Recurso;

public class TresPorUno implements ReglaDeComercio {

    @Override
    public boolean puedeHacerComercioMaritimo(Jugador jugador, Recurso ofrecido) {
        return jugador.tieneCantidadDe(ofrecido, 3);
    }

    @Override
    public void realizarComercioMaritimo(Jugador jugador, Recurso ofrecido, Recurso pedido) {
        jugador.entregarRecurso(ofrecido, 3);
        jugador.recibirRecursoDesdeBanco(pedido);
    }
}