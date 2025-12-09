package model.Catan;

import model.Dados.Dados;
import model.Desarrollo.CartasDesarrollo.ActivacionDesarrollo;
import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Arista.Carretera;
import model.Tablero.Hexagono;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Poblado;

import java.util.List;

public class TurnoGeneral extends Turno {

    public TurnoGeneral(Catan catan, Tablero tablero, Jugador jugador, Dados dados) {
        super(catan, tablero, jugador, dados);
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

    public void moverLadron(Hexagono  hexagono){
        System.out.println("mover ladron a " + hexagono.getNumero());
        this.tablero.moverLadron(hexagono, this.jugador);
    }

    public void construirCarretera(int[] numeroDeArista){
        Carretera carretera = new Carretera(this.jugador);
        if(carretera.jugadorMePuedePagar()){
            if(intentarUbicarCarretera(carretera, numeroDeArista)){
                carretera.cobrarleAJugador();
            }
        }
    }

    public void construirPoblado(int numeroDeVertice){
        Poblado poblado = new Poblado(this.jugador);
        if(poblado.jugadorMePuedePagar()){
            if(intentarUbicarEstructura(poblado, numeroDeVertice)){
                poblado.cobrarleAJugador();
            }
        }
    }

    public void construirCiudad(int numeroDeVertice){
        Ciudad ciudad = new Ciudad(this.jugador);
        if(ciudad.jugadorMePuedePagar()){
            if(intentarUbicarEstructura(ciudad, numeroDeVertice)){
                ciudad.cobrarleAJugador();
            }
        }
    }

    @Override
    public TurnoInicial getTurnoInicial() {
        return null;
    }

    @Override
    public TurnoGeneral getTurnoGeneral() {
        return this;
    }



    public void comprarDesarrollo(){
        if(CartaDesarrollo.jugadorMePuedePagar(this.jugador)){
            this.jugador.adquirirDesarrollo();
        }
    }

    public void comerciar(Jugador jugadorObjetivo, List<Recurso> recurso, List<Recurso> recurso2){
        this.jugador.comerciarConJugador(jugadorObjetivo, recurso, recurso2);
    }

    public ActivacionDesarrollo usarDesarrollo(int posicionDeCarta){
        //se va a cambiar
        return this.jugador.getActivacionParaCartaEnPosicion(posicionDeCarta);
    }

    public String getNombreJugador() {
        return this.jugador.getNombre();
    }

}
