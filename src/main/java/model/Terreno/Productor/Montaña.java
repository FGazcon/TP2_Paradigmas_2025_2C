package model.Terreno.Productor;

import model.Recurso.Recurso;
import model.Recurso.Piedra;

public class Montaña extends Productor {

    public Recurso darRecurso(){
        return new Piedra();
    }

}
