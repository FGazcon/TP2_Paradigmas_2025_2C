package Catan;

import Banco.Banco;
import Errores.*;
import Jugador.Jugador;
import Ladron.Ladron;
import Tablero.Arista.Carretera;
import Tablero.Tablero;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;
import Tablero.Vertice.Estructura.PobladoInicial;

import java.util.ArrayList;
import java.util.List;

public class Catan {

    private Banco banco;
    private List<Jugador> jugadores;
    private Tablero tablero;

    public Catan() {

        this.banco = new Banco();
        this.jugadores = new ArrayList<Jugador>();
        this.tablero = Tablero.crearTableroBasico();

    }
    public Catan(List<Jugador> listaJugadores,Banco banco) {

        this.banco = banco;
        this.jugadores = new ArrayList<Jugador>();
        this.jugadores.addAll(listaJugadores);
        this.tablero = Tablero.crearTableroBasico();


    }
    public void prepararJugadores(){
        this.jugadores = PreparadoDeJugadores.prepararJugadores(this.banco);
    }

    public int intentarUbicarEstructura(Estructura estructura, int numeroDeVerice){
        //Para que tenga sentido, la eleccion del numero de vertice tiene que estar aca.
        try{
            this.tablero.ubicarEstructura(estructura, numeroDeVerice);
        } catch (VerticeNoVacio | VerticeVacio | VerticeOcupadoPorAlguienMas | VerticeOcupadoPorCiudad |
                 VerticeFueraDeAlcance e) {
            System.out.println("No se puede ubicar en un vertice");
            return this.intentarUbicarEstructura(estructura, numeroDeVerice);
        }
        return numeroDeVerice;
    }

    public void intentarUbicarCarretera(Carretera carretera, int numeroDeArista){
        try{
            this.tablero.ubicarCarretera(carretera, numeroDeArista);
        } catch (AristaEstaOcupada | AristaFueraDeAlcance e) {
            System.out.println("No se puede ubicar en un vertice");
            this.intentarUbicarCarretera(carretera, numeroDeArista);
        }
    }

    public void primeraEtapa(){

        int numeroDeVertice = 30;
        for(Jugador jugador: this.jugadores){
            Estructura estructura = new PobladoInicial(jugador);
            intentarUbicarEstructura(estructura, numeroDeVertice);
            numeroDeVertice+=4;
        }

        for(Jugador jugador: this.jugadores){
            Estructura estructura = new PobladoInicial(jugador);
            int verticeSegundoPoblado = intentarUbicarEstructura(estructura, numeroDeVertice);
            this.tablero.activarHexagonoPorVertice(verticeSegundoPoblado);
            numeroDeVertice+=4;
        }

    }


    public void segundaEleccion(Jugador jugador,int verticeElegido){
        Estructura estructura = new PobladoInicial(jugador);
        int verticeSegundoPoblado = intentarUbicarEstructura(estructura, verticeElegido);
        tablero.activarHexagonoPorVertice(verticeSegundoPoblado);
    }



    public void lanzamientoDeDados(int numeroTirado){
        tablero.activarHexagonoPorNumero(numeroTirado);
    }/*
    public void descarte(){
        for(Jugador jugador : this.jugadores){
            jugador.descartarMitad();
        }
    }*/
    public void jugadorMueveLadron(Jugador jugador){
        //ladron.moverLadron();
    }
    public void robo(){

    }/*
    public void resultado7(){
        descarte();
        //jugadorMueveLadron();
        robo();
    }*/
    public void activarLadron(Jugador jugador){
        //ladron.moverLadron();
    }

}
