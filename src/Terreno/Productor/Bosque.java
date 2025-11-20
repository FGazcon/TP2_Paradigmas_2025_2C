package Terreno.Productor;

import Recurso.Recurso;
import Tablero.Vertice.Vertice;
import Recurso.RecursoFactory;

public class Bosque extends Productor {

    public Recurso darRecurso(){
        return RecursoFactory.crearRecurso("Madera");
    }

}
