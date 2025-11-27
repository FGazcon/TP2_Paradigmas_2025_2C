package Comercio;

import Jugador.Jugador;
import Recurso.Recurso;

public class DosPorUno implements ReglaDeComercio {

    private final Class<? extends Recurso> recursoEspecial;

    public DosPorUno(Class<? extends Recurso> recursoEspecial) {
        this.recursoEspecial = recursoEspecial;
    }

    @Override
    public boolean puedeHacerComercioMaritimo(Jugador jugador, Recurso ofrecido) {
        if (!ofrecido.getClass().equals(recursoEspecial)) return false;
        return jugador.tieneCantidadDe(ofrecido, 2);
    }

    @Override
    public void realizarComercioMaritimo(Jugador jugador, Recurso ofrecido, Recurso pedido) {
        jugador.entregarRecurso(ofrecido, 2);
        jugador.recibirRecursoDesdeBanco(pedido);
    }
}