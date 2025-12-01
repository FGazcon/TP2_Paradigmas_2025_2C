package Recurso;

import Jugador.Jugador;

import java.util.List;

public class Ladrillo extends Recurso {

    public Ladrillo(){
        super();
    }

    public Ladrillo(int i) {
        super(i);
    }

    @Override
    public String nombre(){
        return "Ladrillo";
    }

    public static void indicarleAJugadorQueReste(int cantidad, Jugador jugador) {
        jugador.descartarRecurso("Ladrillo", cantidad);
    }

    public static void indicarleAJugadorQueSume(int cantidad, Jugador jugador) {
        jugador.sumarRecurso("Ladrillo", cantidad);
    }

    public static void hacerQueJugadorLePidaAlBanco(int cantidad, Jugador jugador) {
        jugador.pedirAlBanco("Ladrillo", cantidad);
    }

}
