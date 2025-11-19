package Catan;

import Dados.Dados;
import Jugador.Jugador;
import Tablero.Tablero;

public class Turno {

    private Jugador jugador;
    private Tablero tablero;
    private Dados dados;

    public Turno(Jugador jugador, Tablero tablero){
        this.jugador = jugador;
        this.tablero = tablero;
        this.dados = Dados.getDados();
        //empezarTurno();
    }

    public void empezarTurno(){

        int resultadoDados = dados.tirarDados();

        if(resultadoDados == 7){
            //
        } else {
            this.tablero.activarHexagono(resultadoDados);
        }

        //Fase comercio o construccion


        //Fase jugar cartas de desarrollo

        //Fin

    }

}
