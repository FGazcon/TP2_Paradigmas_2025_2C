package model.Catan;

import model.Dados.Dados;
import model.Jugador.Jugador;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.PobladoInicial;

public class SegundoTurnoInicial extends TurnoInicial{

    private int pobladosUbicados = 0;

    public SegundoTurnoInicial(Catan catan, Tablero tablero, Jugador jugador, Dados dados) {
        super(catan, tablero, jugador, dados);
    }

    @Override
    public void construirPoblado(int numeroDeVertice) {
        if (this.pobladosUbicados < 1) {
            if(intentarUbicarEstructura(new PobladoInicial(jugador), numeroDeVertice)){
                pobladosUbicados++;
                tablero.activarHexagonoPorVertice(numeroDeVertice);
            }
        } else {
            System.out.println("Ya construiste poblado.");
        }
    }
}
