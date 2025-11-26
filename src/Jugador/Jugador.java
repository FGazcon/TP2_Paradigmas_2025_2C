package Jugador;

import Banco.Banco;
import Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private List<Recurso> recursos;
    private String nombre;

    public Jugador(String nombre) {
        this.recursos = new ArrayList<Recurso>();
        this.nombre = nombre;
    }

    public void pedirAlBanco(Recurso recurso){

        Banco banco = Banco.getBanco();
        if(banco.darRecurso(recurso)){
            this.recursos.add(recurso);
        }

    }

    public void imprimirRecursos(){
        for (Recurso recurso : this.recursos){
            System.out.println(recurso);
        }
    }

    public void descartarMitad(){
        if(this.cantidadCartas() > 7) {
            descartarPorLadron(cantidadCartas() / 2);
        }
    }

    public void dejarseRobarPorJugador(int cantidadCartasADescartar, Jugador jugador){

        Banco banco = Banco.getBanco();
        //descarta la ultima carta
        for (int i = 0; i < cantidadCartasADescartar; i++) {

            Recurso recursoDescartado = this.recursos.removeLast();
            banco.recibirRecurso(recursoDescartado);
            jugador.pedirAlBanco(recursoDescartado);

        }
    }

    public void descartarPorLadron(int cantidadCartasADescartar){
        Banco banco = Banco.getBanco();
        for (int i = 0; i < cantidadCartasADescartar; i++) {
            banco.recibirRecurso(this.recursos.removeLast());
        }
    }

    public int cantidadCartas(){
        return this.recursos.size();
    }

}
