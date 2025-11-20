package Terreno.Productor;

import Recurso.Recurso;
import Recurso.Madera;

public class Bosque extends Productor {

    public Recurso darRecurso(){
        return new Madera();
    }

}
