package Tablero.Arista;

import Errores.AristaEstaOcupada;
import Jugador.Jugador;

public class AristaOcupada extends EstadoArista {
    @Override
    public void ubicarCarretera(Carretera carretera, Arista arista) {
        throw new AristaEstaOcupada();
    }

    @Override
    public boolean esDe(Jugador jugador, Carretera carretera) {
        return carretera.perteneceA(jugador);
    }
}
