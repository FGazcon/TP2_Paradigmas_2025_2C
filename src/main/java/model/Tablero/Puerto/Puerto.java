package model.Tablero.Puerto;

import model.Recurso.Recurso;
import model.Jugador.Jugador;

public abstract class Puerto {
    protected String nombre;
    protected Recurso recurso;
    //public abstract ReglaDeComercio regla();

    public abstract void darReglaAJugador(Jugador jugador);

    ///////
    /////para la interfaz grafica

    public Recurso getRecurso(){
        return this.recurso;
    }

    public String getNombre(){
        return nombre;
    }
}
