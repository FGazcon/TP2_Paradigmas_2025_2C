package Terreno.Productor;

import Produccion.Piedra;
import Produccion.Recurso;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

public class Montaña extends Productor {

    public Recurso darRecurso() {
        return new Piedra();
    }

}
