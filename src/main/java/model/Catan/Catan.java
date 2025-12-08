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

    public Catan(Banco banco) {
        this.banco = banco;
        this.jugadores = new ArrayList<Jugador>();
        this.tablero = Tablero.crearTableroBasico();
        this.dados = new Dados();
        this.administrador = new AdministradorDeJugadores(this.jugadores);
        this.turno = administrador.nuevoTurno(this, this.tablero, this.dados);
    }

    public Catan(List<Jugador> listaJugadores, Banco banco) {
        this.banco = banco;
        this.jugadores = new ArrayList<Jugador>();
        this.jugadores.addAll(listaJugadores);
        this.tablero = Tablero.crearTableroBasico();
        this.dados = new Dados();
        this.administrador = new AdministradorDeJugadores(this.jugadores);
        this.turno = administrador.nuevoTurno(this, this.tablero, this.dados);

    }

    public void prepararJugadores(){
        this.jugadores = PreparadoDeJugadores.prepararJugadores(this.banco);
        notificar(EventoCatan.PREPARADO_DE_JUGADORES);
    }

    public void terminarTurno() {
        this.turno = turno.terminarTurno(administrador);
        notificar(EventoCatan.CAMBIO_TURNO);
    }

    public void avisarQueSalioLadron() {
        for(Jugador jugador: this.jugadores){
            jugador.descartarMitad();
        }
        notificar(EventoCatan.SALIO_LADRON);
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public void prepararJugadoresConNombres(List<String> nombres) {
        this.jugadores = new ArrayList<>();
        for (String nombre : nombres) {
            this.jugadores.add(new Jugador(nombre, this.banco));
        }
        notificar(EventoCatan.JUGADORES_PREPARADOS_CON_NOMBRE);
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

}