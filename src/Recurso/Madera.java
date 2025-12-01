package Recurso;

import Jugador.Jugador;

public class Madera extends Recurso {

    public Madera(){
        super();
    }

    public Madera(int i) {
        super(i);
    }

    @Override
    public String nombre(){
        return "Madera";
    }

    public static void indicarleAJugadorQueReste(int cantidad, Jugador jugador) {
        jugador.descartarRecurso("Madera", cantidad);
    }

    public static void indicarleAJugadorQueSume(int cantidad, Jugador jugador) {
        jugador.sumarRecurso("Madera", cantidad);
    }

    public static void hacerQueJugadorLePidaAlBanco(int cantidad, Jugador jugador) {
        jugador.pedirAlBanco("Madera", cantidad);
    }

    @Override
    public void darReglaA(Jugador jugador, ReglaDeComercio reglaDeComercio) {
        jugador.darReglaA(this, reglaDeComercio);
    }

}