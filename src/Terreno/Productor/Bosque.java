package Terreno.Productor;

import Produccion.Madera;
import Produccion.Recurso;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

public class Bosque extends Productor {

    public Recurso darRecurso() {
        return new Madera();
    }

}
