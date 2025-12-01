package Catan;

import Banco.Banco;
import Dados.Dados;
import Errores.*;
import Jugador.Jugador;
import Ladron.Ladron;
import Tablero.Arista.Carretera;
import Tablero.Tablero;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;
import Tablero.Vertice.Estructura.PobladoInicial;

import java.util.ArrayList;
import java.util.List;

public class Catan {

    private Banco banco;
    private List<Jugador> jugadores;
    private Tablero tablero;
    private Dados dados;

    public Catan() {

        this.banco = Banco.getBanco();
        this.jugadores = new ArrayList<Jugador>();
        this.tablero = Tablero.crearTableroBasico();
        this.dados = new Dados();

    }

    public Catan(List<Jugador> listaJugadores) {

        this.banco = Banco.getBanco();
        this.jugadores = new ArrayList<Jugador>();
        this.jugadores.addAll(listaJugadores);
        this.tablero = Tablero.crearTableroBasico();


    }

    public void prepararJugadores(){
        this.jugadores = PreparadoDeJugadores.prepararJugadores(this.banco);
    }

    public void cicloDeJuego(){
        for(Jugador jugador: this.jugadores){
            TurnoPersonal turno = new TurnoPersonal(this, this.tablero, jugador, this.dados);
            turno.ejecutarTurno();
        }
    }

    public void avisarQueSalioLadron() {
        for(Jugador jugador: this.jugadores){
            jugador.descartarMitad();
        }
    }

}
