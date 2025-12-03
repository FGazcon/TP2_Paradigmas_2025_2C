package model.Catan;

import model.Dados.Dados;
import model.Errores.*;
import model.Jugador.Jugador;
import model.Tablero.Arista.Carretera;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.PobladoInicial;

import java.util.List;

public class TurnoInicial extends Turno{

    private Jugador jugador;

    public TurnoInicial(Catan catan, Tablero tablero, Jugador jugador, Dados dados){
        super(catan, tablero, jugador, dados);
    }

    public void construirCarretera(int[] numeroDeArista){
        intentarUbicarCarretera(new Carretera(jugador), numeroDeArista);
    }

    public void construirPoblado(int numeroDeVertice){
        intentarUbicarEstructura(new PobladoInicial(jugador), numeroDeVertice);
    }

}
