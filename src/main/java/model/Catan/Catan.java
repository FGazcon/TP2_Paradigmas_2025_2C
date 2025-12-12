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

    private final Banco banco;
    private List<Jugador> jugadores;
    private final Tablero tablero;
    private final Dados dados;
    private Turno turno;
    private final AdministradorDeJugadores administrador;
    private Jugador ganador;
    private Jugador carreteraMasLarga;
    private Jugador caballeriaMasGrande;
 //   private


    public Catan(Banco banco) {
        this.banco = banco;
        this.jugadores = new ArrayList<>();
        this.tablero = Tablero.crearTableroBasico();
        this.dados = new Dados();
        this.administrador = new AdministradorDeJugadores(this.jugadores);
        this.turno = administrador.nuevoTurno(this, this.tablero, this.dados);
        this.carreteraMasLarga= null;
    }

    public Catan(List<Jugador> listaJugadores, Banco banco) {
        this.banco = banco;
        this.jugadores = new ArrayList<>();
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
                if(jugador.superaCuatroCarreteras() != null){
                    this.carreteraMasLarga = jugador.superaCuatroCarreteras();
                } else {
                    break;
                }
            }
            this.carreteraMasLarga.setPuntosCarretera(0);
            this.carreteraMasLarga = jugador.carreteraMasLarga(this.carreteraMasLarga);
        }
        if(this.carreteraMasLarga != null ){
            this.carreteraMasLarga.setPuntosCarretera(1);
        }
    }

    public void caballeriaMasGrande(){
        for (Jugador jugador : this.jugadores){
            if(this.caballeriaMasGrande == null){
                if(jugador.superaTresCaballeros() != null){
                    this.caballeriaMasGrande = jugador.superaTresCaballeros();
                } else {
                    break;
                }
            }
            this.caballeriaMasGrande.setPuntosCaballeria(0);
            this.caballeriaMasGrande = jugador.caballeriaMasLarga(this.carreteraMasLarga);
        }
        if(this.caballeriaMasGrande != null ){
            this.caballeriaMasGrande.setPuntosCaballeria(1);
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

    public void revisarPuntaje(Jugador jugador){
        if(jugador.calcularPuntaje() >= 10){
            this.declararGanador(jugador);
        }
    }

}