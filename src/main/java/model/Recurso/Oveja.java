package model.Recurso;

import model.Banco.Banco;
import model.Comercio.ReglaDeComercio;
import model.Jugador.Jugador;

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

    public boolean bancoTieneAlMenos(Banco banco, int cantidad){
        return banco.tieneAlMenos(this, cantidad);
    }

    @Override
    public boolean tieneAlMenos(int cantidad){
        return this.cantidad >= cantidad;
    }

    @Override
    public void darReglaA(Jugador jugador, ReglaDeComercio reglaDeComercio) {
        jugador.darReglaA(this, reglaDeComercio);
    }

    public Recurso getRecursoJugador(Jugador jugador){
        return  jugador.getOveja();
    }

    public Recurso getRecursoBanco(Banco banco){
        return  banco.getOveja();
    }
}