package Catan;

import Dados.Dados;
import Jugador.Jugador;
import Tablero.Tablero;

public class TurnoPersonal extends Turno{

    private Tablero tablero;
    private Dados dados;
    private Jugador jugador;
    private Catan catan;

    public TurnoPersonal(Catan catan, Tablero tablero, Jugador jugador, Dados dados){
        super(tablero);
        this.tablero = tablero;
        this.catan = catan;
        this.dados = dados;
        this.jugador = jugador;
    }

    public void ejecutarTurno(){

        //Dados

        //Comercio

        //Construccion

        //Desarrollo

    }

    public void tirarDados(){

        int resultadoDados = dados.tirarDados();

        if(resultadoDados == 7){
            catan.avisarQueSalioLadron();

            //El jugador elige a donde
            tablero.moverLadron(0, jugador);

        } else {

            tablero.activarHexagonoPorNumero(resultadoDados);

        }
    }

    public void construirCarretera(){

    }

    public void construirPoblado(){

    }

    public void construirCiudad(){

    }

    public void comerciar(){

    }

    public void usarDesarrollo(){

    }

    public void terminarTurno(){

    }

}
