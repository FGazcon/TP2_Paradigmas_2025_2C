package model.Terreno;

import model.Recurso.Recurso;
import model.Terreno.Productor.Productor;

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
