package model.Terreno.Productor;

import model.Recurso.Recurso;
import model.Recurso.Oveja;
public class Pastizal extends Productor {

    public Recurso darRecurso(){
       // vertice.darRecurso(java.Recurso.OVEJA);
        return new Oveja();
    }

}
