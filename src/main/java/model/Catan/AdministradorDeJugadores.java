package model.Catan;

import model.Jugador.Jugador;
import model.Tablero.Tablero;
import model.Dados.Dados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdministradorDeJugadores {

    //Jugadores Ida
    //Jugadores Vuelta
    private ArrayList<Jugador> jugadoresIda;
    private ArrayList<Jugador> jugadoresVuelta;

    private Queue<Jugador> jugadores;

    public AdministradorDeJugadores(List<Jugador> jugadores){

        this.jugadores = new LinkedList<Jugador>();
        this.jugadores.addAll(jugadores);

        this.jugadoresIda = new ArrayList<Jugador>();
        this.jugadoresIda.addAll(jugadores);

        this.jugadoresVuelta = new ArrayList<Jugador>();
        for(Jugador jugador : jugadores){
            this.jugadoresVuelta.addFirst(jugador);
        }

    }

    public Turno nuevoTurno(Catan catan, Tablero tablero, Dados dados){
        if(!jugadoresIda.isEmpty()){
            return new TurnoInicial(catan, tablero, jugadoresIda.removeFirst(), dados);
        }

        if(!jugadoresVuelta.isEmpty()){
            return new TurnoInicial(catan, tablero, jugadoresVuelta.removeFirst(), dados);
        }


        Jugador sig = jugadores.poll();

        jugadores.add(sig);

        return new TurnoGeneral(catan, tablero, sig, dados);
    }


}
