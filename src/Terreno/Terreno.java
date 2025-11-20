package Terreno;

import Recurso.Recurso;
import Terreno.Productor.Productor;

import java.util.List;

public abstract class Terreno {

    public static List<Terreno> generar19Terrenos(){

        List<Terreno> terrenos = Productor.generar18TerrenosProductores();

        terrenos.add(new Desierto());

        return terrenos;
    }

    //Urgente consultar si no rompe con DIP.
    public abstract Recurso darRecurso();

}
