package Terreno.Productor;

import Recurso.Recurso;
import Tablero.Vertice.Vertice;
import Recurso.Madera;

public class Bosque extends Productor {

    public void darRecurso(Vertice vertice){
        vertice.darRecurso(new Madera());
    }

}
