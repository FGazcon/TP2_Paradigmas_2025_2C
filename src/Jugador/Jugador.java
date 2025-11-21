package Jugador;

import Banco.Banco;
import Negociantes.Negociantes;
import Produccion.Carta;
import Produccion.MazoProduccion;
import Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends Negociantes{

    private List<Carta> cartas;
    private String nombre;

    public Jugador(String nombre) {
        this.cartas = new ArrayList<Carta>();
        this.nombre = nombre;
    }

    public void pedirAlBanco(Recurso recurso){

        Banco banco = Banco.getBanco();
        Carta cartanueva = banco.darRecurso(recurso);
        System.out.println(cartanueva);
        cartas.add(cartanueva);
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
            //Falta que el banco reciba e alguna manera

        }
    }

    public int cantidadCartas(){
        return this.cartas.size();
    }

}
