package Recurso;

import Banco.Banco;
import Comercio.ReglaDeComercio;
import Jugador.Jugador;

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

