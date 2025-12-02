package model.Terreno.Productor;

import model.Recurso.Recurso;
import model.Recurso.Madera;

public class Bosque extends Productor {

    public Recurso darRecurso(){
        return new Madera();
    }

}
