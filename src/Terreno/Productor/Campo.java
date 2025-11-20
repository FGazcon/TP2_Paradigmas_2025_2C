package Terreno.Productor;

import Recurso.Recurso;
import Recurso.Trigo;

public class Campo extends Productor {

    public Recurso darRecurso(){
       return new Trigo();
    }

}
