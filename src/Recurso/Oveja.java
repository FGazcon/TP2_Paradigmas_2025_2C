package Recurso;

import Jugador.Jugador;

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

    public void transferirAlBanco(Banco banco, int cantidad){
        banco.sumarRecurso(this, cantidad);
    }

    public void transferirAJugador(Jugador jugador, int cantidad){
        jugador.sumarRecurso(this, cantidad);
    }

    public void descartarAlBanco(Banco banco, int cantidad){
        banco.descartarRecurso(this, cantidad);
    }

    public void descartarAJugador(Jugador jugador, int cantidad){
        jugador.descartarRecurso(this, cantidad);
    }

    public void hacerQuejugadorSoliciteABanco(Jugador jugador, int cantidad){
        jugador.pedirAlBanco(this, cantidad);
    }

    public boolean jugadorTieneAlMenos(Jugador jugador, int cantidad){
        return jugador.tieneAlMenos(this, cantidad);
    }

    @Override
    public void darReglaA(Jugador jugador, ReglaDeComercio reglaDeComercio) {
        jugador.darReglaA(this, reglaDeComercio);
    }
}