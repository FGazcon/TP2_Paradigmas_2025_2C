package Terreno.Productor;

import Produccion.Recurso;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

public class Campo extends Productor {

    public void darRecurso(Vertice vertice){
        vertice.darRecurso(Recurso.TRIGO);
    }

}
