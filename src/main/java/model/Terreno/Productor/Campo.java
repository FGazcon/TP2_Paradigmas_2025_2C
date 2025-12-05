package model.Terreno.Productor;

import model.Recurso.Recurso;
import model.Recurso.Trigo;

public class Campo extends Productor {

    public Recurso darRecurso(){
       return new Trigo();
    }

}
