package Catan;

import Banco.Banco;
import Errores.VerticeNoVacio;
import Errores.VerticeOcupadoPorAlguienMas;
import Errores.VerticeVacio;
import Jugador.Jugador;
import Recurso.Recurso;
import Tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Catan {

    private Banco banco;
    private List<Jugador> jugadores;
    private Tablero tablero;

    public Catan() {

        this.banco = new Banco();
        this.jugadores = new ArrayList<Jugador>();
        this.tablero = new Tablero();
    }
    public Catan(List<Jugador> listaJugadores,Banco banco) {

        this.banco = banco;
        this.jugadores = new ArrayList<Jugador>();
        this.jugadores.addAll(listaJugadores);
        this.tablero = new Tablero();
    }
    public void prepararJugadores(){
        this.jugadores = PreparadoDeJugadores.prepararJugadores(this.banco);
    }

    public int intentarUbicarPoblado(Jugador jugador, int numeroDeVerice){

        //Para que tenga sentido, la eleccion del numero de vertice tiene que estar aca.

        try{
            this.tablero.ubicarPoblado(jugador, numeroDeVerice);
        } catch (VerticeNoVacio e) {
            System.out.println("No se puede ubicar un vertice");
            return this.intentarUbicarPoblado(jugador, numeroDeVerice);
        }
        return numeroDeVerice;
    }

    public int intentarUbicarCiudad(Jugador jugador, int numeroDeVerice){

        //Para que tenga sentido, la eleccion del numero de vertice tiene que estar aca.

        try{
            this.tablero.ubicarCiudad(jugador, numeroDeVerice);
        } catch (VerticeVacio | VerticeOcupadoPorAlguienMas e) {
            System.out.println("No se puede ubicar un vertice");
            return this.intentarUbicarCiudad(jugador, numeroDeVerice);
        }
        return numeroDeVerice;
    }

    public void primeraEtapa(){

        int numeroDeVertice = 30;
        for(Jugador jugador: this.jugadores){
            intentarUbicarPoblado(jugador, numeroDeVertice);
            numeroDeVertice+=4;
        }

        for(Jugador jugador: this.jugadores){
            int verticeSegundoPoblado = intentarUbicarPoblado(jugador, numeroDeVertice);
            this.tablero.activarHexagonoParaSegundoPoblado(verticeSegundoPoblado);
            numeroDeVertice+=4;
        }

    }


    public void segundaEleccion(Jugador jugador,int verticeElegido){

        intentarUbicarPoblado(jugador, verticeElegido);
        tablero.activarHexagonoParaSegundoPoblado(verticeElegido);

    }



    public void lanzamientoDeDados(int numeroTirado){
        tablero.activarHexagono(numeroTirado);
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
