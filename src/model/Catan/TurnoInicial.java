package model.Catan;

import model.Dados.Dados;
import model.Errores.*;
import model.Jugador.Jugador;
import model.Tablero.Arista.Carretera;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.PobladoInicial;

import java.util.List;

public class TurnoInicial extends Turno {

    private int pobladosUbicados = 0;
    private int carreterasUbicadas = 0;

    public TurnoInicial(Catan catan, Tablero tablero, Jugador jugador, Dados dados) {
        super(catan, tablero, jugador, dados);
    }
    @Override
    public void construirCarretera(int[] numeroDeArista) {
        if (this.pobladosUbicados < 1) {
            if(intentarUbicarCarretera(new Carretera(jugador), numeroDeArista)){
                pobladosUbicados = 1;
            }
        }
    }
    @Override
    public void construirPoblado(int numeroDeVertice) {
        System.out.println(this.jugador.getNombre());
        if (this.carreterasUbicadas < 1) {
            if(intentarUbicarEstructura(new PobladoInicial(jugador), numeroDeVertice)){
                carreterasUbicadas++;
            }
        }
    }
}