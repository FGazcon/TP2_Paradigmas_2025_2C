package Terreno.Productor;

import Recurso.Recurso;
import Recurso.Oveja;
public class Pastizal extends Productor {

    public Recurso darRecurso(){
       // vertice.darRecurso(Recurso.OVEJA);
        return new Oveja();
    }

}
