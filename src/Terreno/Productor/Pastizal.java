package Terreno.Productor;

import Produccion.Oveja;
import Produccion.Recurso;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

public class Pastizal extends Productor {

    public Recurso darRecurso() {
        return new Oveja();
    }

}
