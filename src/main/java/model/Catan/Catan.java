package model.Catan;

import model.Banco.Banco;
import model.Dados.Dados;
import model.EventoCatan;
import model.Jugador.Jugador;
import model.Observable;
import model.Tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Catan extends Observable {

    private Banco banco;
    private List<Jugador> jugadores;
    private Tablero tablero;
    private Dados dados;
    private Turno turno;
    private AdministradorDeJugadores administrador;
    private Jugador ganador;
    private Jugador carreteraMasLarga;
 //   private


    public Catan(Banco banco) {
        this.banco = banco;
        this.jugadores = new ArrayList<Jugador>();
        this.tablero = Tablero.crearTableroBasico();
        this.dados = new Dados();
        this.administrador = new AdministradorDeJugadores(this.jugadores);
        this.turno = administrador.nuevoTurno(this, this.tablero, this.dados);
        this.carreteraMasLarga= null;
    }

    public Catan(List<Jugador> listaJugadores, Banco banco) {
        this.banco = banco;
        this.jugadores = new ArrayList<Jugador>();
        this.jugadores.addAll(listaJugadores);
        this.tablero = Tablero.crearTableroBasico();
        this.dados = new Dados();
        this.administrador = new AdministradorDeJugadores(this.jugadores);
        this.turno = administrador.nuevoTurno(this, this.tablero, this.dados);
        this.carreteraMasLarga = null;

    }

    public void prepararJugadores(){
        this.jugadores = PreparadoDeJugadores.prepararJugadores(this.banco);
    }

    public void terminarTurno() {
        Turno turnoAnterior = this.turno;

        this.turno = turno.terminarTurno(administrador);

        if (turnoAnterior instanceof TurnoInicial && this.turno instanceof TurnoInicial) {
            avisar(EventoCatan.CAMBIO_TURNO_INICIAL);
        }

        if (turnoAnterior instanceof TurnoInicial && this.turno instanceof TurnoGeneral) {
            avisar(EventoCatan.FIN_TURNO_INICIAL);
        }

        if (turnoAnterior instanceof TurnoGeneral && this.turno instanceof TurnoGeneral) {
            avisar(EventoCatan.CAMBIO_TURNO_GENERAL);
        }
    }

    public void carreteraMasLarga(){
        for (Jugador jugador : this.jugadores){
            if(this.carreteraMasLarga == null){
                this.carreteraMasLarga = jugador.superaCuatroCarreteras();
            }
            this.carreteraMasLarga.setPuntosCarretera(0);
            this.carreteraMasLarga = jugador.carreteraMasLarga(this.carreteraMasLarga);
        }
        this.carreteraMasLarga.setPuntosCarretera(1);

    }


    public void avisarQueSalioLadron() {
        for(Jugador jugador: this.jugadores){
            jugador.descartarMitad();
        }
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public void prepararJugadoresConNombres(List<String> nombres) {
        this.jugadores = new ArrayList<>();
        for (String nombre : nombres) {
            this.jugadores.add(new Jugador(nombre, this.banco));
        }
    }

    public Turno getTurno(){
        return this.turno;
    }

    public List<Jugador> getJugadores(){
        return jugadores;
    }

    public void declararGanador(Jugador j) {
        this.ganador = j;
        notificar(EventoCatan.GANADOR);
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void avisar(EventoCatan evento) {
        notificar(evento);
    }

}