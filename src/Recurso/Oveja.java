package Recurso;

import Jugador.Jugador;

import java.util.List;

public class Oveja extends Recurso {

    public Oveja(){
        super();
    }

    public Oveja(int i) {
        super(i);
    }

    @Override
    public String nombre(){
        return "Oveja";
    }

    public static void indicarleAJugadorQueReste(int cantidad, Jugador jugador) {
        jugador.descartarRecurso("Oveja", cantidad);
    }

    public static void indicarleAJugadorQueSume(int cantidad, Jugador jugador) {
        jugador.sumarRecurso("Oveja", cantidad);
    }

    public static void hacerQueJugadorLePidaAlBanco(int cantidad, Jugador jugador) {
        jugador.pedirAlBanco("Oveja", cantidad);
    }

}