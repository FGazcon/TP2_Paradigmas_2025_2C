package Terreno;

import Terreno.Productor.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Terreno {

    public static List<Terreno> generar19Terrenos(){

        List<Terreno> terrenos = Productor.generar18TerrenosProductores();

        terrenos.add(new Desierto());

        return terrenos;
    }

}
