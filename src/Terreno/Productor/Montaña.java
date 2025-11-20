package Terreno.Productor;

import Recurso.Recurso;
import Recurso.Piedra;

public class Montaña extends Productor {

    public Recurso darRecurso(){
        return new Piedra();
    }

}
