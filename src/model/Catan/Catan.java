package model.Catan;

import model.Banco.Banco;
import model.Dados.Dados;
import model.Errores.*;
import model.Jugador.Jugador;
import model.Tablero.Tablero;

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

    public void ejecutarTurnoInicial(){
        TurnoInicial turnoInicial = new TurnoInicial(this.tablero, this.jugadores);
        turnoInicial.primeraEtapa();
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

    public Tablero getTablero() {
        return this.tablero;
    }

}
