package Terreno.Productor;

import Recurso.Recurso;
import Tablero.Vertice.Vertice;

public class Colina extends Productor {

    public void darRecurso(Vertice vertice){
        vertice.darRecurso(Recurso.LADRILLO);
    }

}
