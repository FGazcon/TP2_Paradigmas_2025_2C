package model.Catan;

import model.Dados.Dados;
import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Jugador.Jugador;
import model.Tablero.Arista.Carretera;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.*;

public class TurnoGeneral extends Turno {

    public TurnoGeneral(Catan catan, Tablero tablero, Jugador jugador, Dados dados) {
        super(catan, tablero, jugador, dados);
    }

    public void ejecutarTurno() {

        //Dados

        //Comercio

        //Construccion

        //Desarrollo

    }

    public void tirarDados(int resultadoDados) {

        if (resultadoDados == 7) {
            catan.avisarQueSalioLadron();

            //El jugador elige a donde
            //tablero.moverLadron(0, jugador);

        } else {

            tablero.activarHexagonoPorNumero(resultadoDados);

        }
    }

    public void moverLadron(int numeroDeHexagono){
        this.tablero.moverLadron(numeroDeHexagono, this.jugador);
    }

    public void construirCarretera(int[] numeroDeArista){
        Carretera carretera = new Carretera(this.jugador);
        if(carretera.jugadorMePuedePagar()){
            tablero.ubicarCarretera(carretera, numeroDeArista);
        }
    }

    public void construirPoblado(int numeroDeVertice){
        Poblado poblado = new Poblado(this.jugador);
        if(poblado.jugadorMePuedePagar()){
            tablero.ubicarEstructura(poblado, numeroDeVertice);
        }
    }

    public void construirCiudad(int numeroDeVertice){
        Ciudad ciudad = new Ciudad(this.jugador);
        if(ciudad.jugadorMePuedePagar()){
            tablero.ubicarEstructura(ciudad, numeroDeVertice);
        }
    }

    public void comprarDesarrollo(){
        if(CartaDesarrollo.jugadorMePuedePagar(this.jugador)){

        }
    }

    public void comerciar(){

    }

    public void usarDesarrollo(){

    }

    public String getNombreJugador() {
        return this.jugador.getNombre();
    }

}
