package Comercio;

import Jugador.Jugador;
import Recurso.Recurso;

public interface ReglaDeComercio {

    boolean puedeHacerComercioMaritimo(Jugador jugador, Recurso ofrecido);

    void realizarComercioMaritimo(Jugador jugador, Recurso ofrecido, Recurso pedido);
}