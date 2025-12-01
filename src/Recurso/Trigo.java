package Recurso;

import Jugador.Jugador;

import java.util.List;

public class Trigo extends Recurso {

    public Trigo(){
        super();
    }

    public Trigo(int i) {
        super(i);
    }

    @Override
    public String nombre(){
        return "Trigo";
    }

    public static void indicarleAJugadorQueReste(int cantidad, Jugador jugador) {
        jugador.descartarRecurso("Trigo", cantidad);
    }

    public static void indicarleAJugadorQueSume(int cantidad, Jugador jugador) {
        jugador.sumarRecurso("Trigo", cantidad);
    }

    public static void hacerQueJugadorLePidaAlBanco(int cantidad, Jugador jugador) {
        jugador.pedirAlBanco("Trigo", cantidad);
    }

}

