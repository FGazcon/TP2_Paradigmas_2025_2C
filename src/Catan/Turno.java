package Catan;

import Dados.Dados;
import Jugador.Jugador;
import Tablero.Tablero;

public class Turno {

    public void ejecutarTurno(Catan catan, Tablero tablero, Jugador jugador, Dados dados){

        int resultadoDados = dados.tirarDados();

        if(resultadoDados == 7){
            catan.avisarQueSalioLadron();

            //El jugador elige a donde
            tablero.moverLadron(0, jugador);

        } else {

            tablero.activarHexagonoPorNumero(resultadoDados);

        }

        //Comercio

        //Construccion

        //Desarrollo

    }

}
