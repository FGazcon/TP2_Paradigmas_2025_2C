package Recurso;

import Jugador.Jugador;

import java.util.List;

public class Piedra extends Recurso {

    public Piedra(){
        super();
    }

    public Piedra(int i) {
        super(i);
    }

    @Override
    public String nombre(){
        return "Piedra";
    }

    public static void indicarleAJugadorQueReste(int cantidad, Jugador jugador) {
        jugador.descartarRecurso("Piedra", cantidad);
    }

    public static void indicarleAJugadorQueSume(int cantidad, Jugador jugador) {
        jugador.sumarRecurso("Piedra", cantidad);
    }

    public static void hacerQueJugadorLePidaAlBanco(int cantidad, Jugador jugador) {
        jugador.pedirAlBanco("Piedra", cantidad);
    }

}
