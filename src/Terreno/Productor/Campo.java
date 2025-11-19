package Terreno.Productor;

import Produccion.Recurso;
import Produccion.Trigo;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

public class Campo extends Productor {

    public Recurso darRecurso() {
        return new Trigo();
    }

}
