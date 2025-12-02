package model.Catan;

import model.Errores.*;
import model.Jugador.Jugador;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.PobladoInicial;

import java.util.List;

public class TurnoInicial extends Turno{
    
    private Tablero tablero;
    private List<Jugador> jugadores;
    
    public TurnoInicial(Tablero tablero, List<Jugador> jugadores){
        super(tablero);
        this.tablero = tablero;
        this.jugadores = jugadores;
    }

    public void primeraEtapa(){

        int numeroDeVertice = 30;
        for(Jugador jugador: this.jugadores){
            Estructura estructura = new PobladoInicial(jugador);
            intentarUbicarEstructura(estructura, numeroDeVertice);
            numeroDeVertice+=4;
        }

        for(Jugador jugador: this.jugadores){
            segundaEleccion(jugador, numeroDeVertice);
        }

    }

    public void segundaEleccion(Jugador jugador,int verticeElegido){
        Estructura estructura = new PobladoInicial(jugador);
        int verticeSegundoPoblado = intentarUbicarEstructura(estructura, verticeElegido);
        tablero.activarHexagonoPorVertice(verticeSegundoPoblado);
    }
    
}
