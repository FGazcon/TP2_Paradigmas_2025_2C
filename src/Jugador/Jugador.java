package Jugador;

import Banco.Banco;
import Produccion.Carta;
import Recurso.Recurso;
import Tablero.Factory.Factory_MapaBasico;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private List<Carta> cartas;
    private String nombre;

    public Jugador(String nombre) {
        this.cartas = new ArrayList<Carta>();
        this.nombre = nombre;
    }

    public void pedirAlBanco(Recurso recurso){

        Banco banco = Banco.getBanco();
        Carta cartaNueva = banco.darRecurso(recurso);
        System.out.println(cartaNueva);
        sumarCarta(cartaNueva);
    }

    public void imprimirRecursos(){
        for (Carta carta : cartas){
            System.out.println(carta);
        }
    }

    public void descartarMitad(){
        if(this.cantidadCartas() > 7) {
            descartarCarta(cantidadCartas() / 2);
        }
    }

    public void descartarCarta(int cantidadCartasADescartar, Jugador jugador){
        Banco banco = Banco.getBanco();
        //descarta la ultima carta
        for (int i = 0; i < cantidadCartasADescartar; i++) {

            Carta cartaDescartada = this.cartas.removeLast();
            jugador.pedirAlBanco(cartaDescartada.getRecurso());

        }
    }

    public void descartarCarta(int cantidadCartasADescartar){
        Banco banco = Banco.getBanco();
        //descarta la ultima carta
        for (int i = 0; i < cantidadCartasADescartar; i++) {

            Carta cartaDescartada = this.cartas.removeLast();
            //Falta que el banco reciba de alguna manera

        }
    }
    public Carta cartaRobada(){
        //se podria hacer un aleatorio de cartas en el factory mapaBasico haciendo que sea un FactoryRandom y mezclar las cartas
        //y devolver un mazo mezclado y remover una
        Carta.mezclarCartas(this.cartas);
        return this.cartas.removeLast();

    }
    public void sumarCarta(Carta carta){
        this.cartas.add(carta);
    }

    public int cantidadCartas(){
        return this.cartas.size();
    }

}
