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
import model.EventoCatan;

import java.util.List;

public class TurnoGeneral extends Turno {

    public TurnoGeneral(Catan catan, Tablero tablero, Jugador jugador, Dados dados) {
        super(catan, tablero, jugador, dados);
    }

    public void tirarDados(int resultadoDados) {
        catan.avisar(EventoCatan.DADOS_TIRADOS);

        if (resultadoDados == 7) {
            catan.avisarQueSalioLadron();
        }

        else {
            tablero.activarHexagonoPorNumero(resultadoDados);
            catan.avisar(EventoCatan.RECURSOS_ENTREGADOS);
        }
    }

    public void moverLadron(Hexagono  hexagono){
        System.out.println("mover ladron a " + hexagono.getNumero());
        this.tablero.moverLadron(hexagono, this.jugador);
        catan.avisar(EventoCatan.LADRON_MOVIDO);
        catan.caballeriaMasGrande();

    }

    public void construirCarretera(int[] numeroDeArista){
        Carretera carretera = new Carretera(this.jugador);
        if(carretera.jugadorMePuedePagar()){
            if(intentarUbicarCarretera(carretera, numeroDeArista)){
                carretera.cobrarleAJugador();
                verificarCarreteraMasLarga();
                catan.avisar(EventoCatan.CARRETERA_CONSTRUIDA);
            }
        }
    }

    public void verificarCarreteraMasLarga(){
        catan.carreteraMasLarga();
    }

    public void construirPoblado(int numeroDeVertice){
        Poblado poblado = new Poblado(this.jugador);
        if(poblado.jugadorMePuedePagar()){
            if(intentarUbicarEstructura(poblado, numeroDeVertice)){
                poblado.cobrarleAJugador();
                catan.avisar(EventoCatan.POBLADO_CONSTRUIDO);
            }
        }
    }

    public void construirCiudad(int numeroDeVertice){
        Ciudad ciudad = new Ciudad(this.jugador);
        if(ciudad.jugadorMePuedePagar()){
            if(intentarUbicarEstructura(ciudad, numeroDeVertice)){
                ciudad.cobrarleAJugador();
                catan.avisar(EventoCatan.CIUDAD_CONSTRUIDA);
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
            catan.avisar(EventoCatan.DESARROLLO_COMPRADO);
            this.catan.revisarPuntaje(this.jugador);
        }
    }

    public void comerciar(Jugador jugadorObjetivo, List<Recurso> recurso, List<Recurso> recurso2){
        this.jugador.comerciarConJugador(jugadorObjetivo, recurso, recurso2);
        catan.avisar(EventoCatan.COMERCIO_CON_JUGADOR_REALIZADO);
    }

    public void comerciarConBanco(Recurso recursoOfertado,int cantidad,Recurso recursoBuscado){
        this.jugador.comerciarConBanco(recursoOfertado,cantidad,recursoBuscado);
        catan.avisar(EventoCatan.COMERCIO_CON_BANCO_REALIZADO);
    }

    public ActivacionDesarrollo usarDesarrollo(int posicionDeCarta){
        return this.jugador.getActivacionParaCartaEnPosicion(posicionDeCarta);
    }

    public String getNombreJugador() {
        return this.jugador.getNombre();
    }

}
