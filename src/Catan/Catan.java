package Catan;

import Banco.Banco;
import Dados.Dados;
import Jugador.Jugador;
import Ladron.Ladron;
import Tablero.Tablero;

public class Catan {

    private Banco banco;
    private Jugador[] jugadores;
    private Tablero tablero;
    private Dados dados;
    private Ladron ladron;

    public Catan() {

        this.banco = new Banco();
        this.jugadores = new Jugador[4];
        this.tablero = new Tablero();
        this.dados = new Dados();
        this.ladron = new Ladron(null);

    }

}
