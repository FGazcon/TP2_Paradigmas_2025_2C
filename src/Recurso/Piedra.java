package Recurso;

import Jugador.Jugador;

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

}
