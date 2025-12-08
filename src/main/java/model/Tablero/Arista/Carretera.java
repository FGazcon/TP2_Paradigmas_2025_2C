package model.Tablero.Arista;

import model.Jugador.Jugador;
import model.Recurso.Ladrillo;
import model.Recurso.Madera;
import model.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Carretera {

    private Jugador jugador;
    private List<Recurso> costo;

    public Carretera(Jugador jugador) {
        this.jugador = jugador;
        this.costo = new ArrayList<>();

        this.costo.add(new Madera(1));
        this.costo.add(new Ladrillo(1));
    }


    public Jugador getJugador() {
        return jugador;
    }

    //Ver si podemos sacar esto. Arreglar mecanica entera de chequeo de adyacencia.
    public boolean perteneceA(Jugador jugador) {
        return this.jugador == jugador;
    }

    public boolean jugadorMePuedePagar(){
        return this.jugador.tieneSuficientesParaOfertar(this.costo);
    }

    public void cobrarleAJugador(){
        this.jugador.pagarleAlBanco(this.costo);
    }

    public void actualizarDistanciaJugador(int i) {
        this.jugador.setCaminoMasLArgo(i);
    }
}
