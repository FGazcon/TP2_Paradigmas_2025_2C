package model.Tablero.Vertice.Estructura;

import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Vertice.Estado;
import model.Tablero.Vertice.Vertice;

import java.util.List;

public abstract class Estructura {

    protected Jugador jugador;
    protected List<Recurso> costo;

    public abstract void ubicarseEnVerticeEnEstado(Estado estado, Vertice vertice);

    public abstract void entregarRecursos(Recurso recurso);

    //Ley de Demeter, cambiar esto a que el vertice/Estructura le avise al jugador que fue robado
    public abstract List<Jugador> anotarDuenio(List<Jugador> jugadores);

    public abstract void intentarMejorar(Ciudad estructura, Vertice vertice);

    public abstract void intentarMejorar(Poblado estructura, Vertice vertice);

    public boolean esDe(Jugador jugador) {
        //ESTAM FUNDAMENTALMENTE MAL, CONSIDERAR COMO LO PODEMOS CAMBIAR
        return jugador ==  this.jugador;
    }
    public Jugador getJugador() {
        return this.jugador;
    }

    public void sumarAJugador() {
        this.jugador.sumarPunto();
    }

    public boolean jugadorMePuedePagar(){
        return this.jugador.tieneSuficientesParaOfertar(this.costo);
    }

    public void cobrarleAJugador(){
        this.jugador.pagarleAlBanco(this.costo);
    }
}
