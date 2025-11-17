package Catan;

import Banco.Banco;
import Dados.Dados;
import Jugador.Jugador;
import Ladron.Ladron;
import Tablero.Tablero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catan {

    private Banco banco;
    private List<Jugador> jugadores;
    private Tablero tablero;
    private Dados dados;
    private Ladron ladron;

    public Catan() {

        this.banco = new Banco();
        this.jugadores = new ArrayList<Jugador>();
        this.tablero = new Tablero();
        this.dados = new Dados();
        this.ladron = new Ladron(null);

    }

    public void armadoJugadores(){

        this.jugadores.add(new Jugador(this.banco));

    }

}
