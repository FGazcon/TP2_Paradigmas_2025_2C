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

    public void descartarCarta(int cantidadCartasADescartar){
        //descarta la ultima carta
        this.cartas.removeLast();
    }

    public int cantidadCartas(){
        return this.cartas.size();
    }

}
