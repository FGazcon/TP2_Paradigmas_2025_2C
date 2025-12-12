package model.Tablero.Vertice.Estructura;

import model.Jugador.Jugador;
import model.Tablero.Vertice.Estado;
import model.Tablero.Vertice.Vertice;

public class PobladoInicial extends Poblado{

    public PobladoInicial(Jugador jugador) {
        super(jugador);
    }

    @Override
    public void ubicarseEnVerticeEnEstado(Estado estado, Vertice vertice) {
        estado.intentarUbicarEstructura(this, vertice);
    }
}
