package Tablero;

import Jugador.Jugador;
import Tablero.Vertice.Vertice;

import java.util.List;

public class Tablero {

    private List<Hexagono> hexagonos;

    public Tablero() {

        this.hexagonos = Hexagono.generar19Hexagonos();

    }

    public void ubicarPoblado(int numeroDeVertice){
        int encontrado = 0;
        while(encontrado >= 0){
            if(hexagonos.get(encontrado).construyePoblado(numeroDeVertice)){
                return;
            }
            encontrado++;
        }
    }

    /*
    public Hexagono buscarDesierto() {

        Hexagono hexagonoDesierto = null;

        for (int i = 0; i < 19; i++) {

            if (this.hexagonos[i].esDesierto()) {
                hexagonoDesierto = this.hexagonos[i];
            }

        }

        return hexagonoDesierto;

    }
    */

}
