package model.Catan;

import model.Dados.Dados;
import model.Jugador.Jugador;
import model.Tablero.Arista.Carretera;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.PobladoInicial;
import model.EventoCatan;

public class TurnoInicial extends Turno {

    private int pobladosUbicados = 0;
    private int carreterasUbicadas = 0;

    public TurnoInicial(Catan catan, Tablero tablero, Jugador jugador, Dados dados) {
        super(catan, tablero, jugador, dados);
    }
    @Override
    public void construirCarretera(int[] numeroDeArista) {
        if (this.carreterasUbicadas < 1) {
            if(intentarUbicarCarretera(new Carretera(jugador), numeroDeArista)){
                carreterasUbicadas = 1;
                catan.avisar(EventoCatan.CARRETERA_INICIAL_CONSTRUIDA);
            }
        } else {
            System.out.println("Ya construiste carretera.");
        }
    }
    @Override
    public void construirPoblado(int numeroDeVertice) {
        if (this.pobladosUbicados < 1) {
            if(intentarUbicarEstructura(new PobladoInicial(jugador), numeroDeVertice)){
                pobladosUbicados++;
                catan.avisar(EventoCatan.POBLADO_INICIAL_CONSTRUIDO);
            }
        } else {
            System.out.println("Ya construiste poblado.");
        }
    }

    @Override
    public TurnoGeneral getTurnoGeneral() {
        return null;
    }

    @Override
    public TurnoInicial getTurnoInicial() {
        return this;
    }
}