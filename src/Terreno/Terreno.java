package Terreno;

import Tablero.Vertice.Vertice;
import Terreno.Productor.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Terreno {

    public static List<Terreno> generar19Terrenos(){

        List<Terreno> terrenos = new ArrayList<Terreno>();

        for(int i = 0; i < 4; i++){
            terrenos.add(new Bosque());
            terrenos.add(new Campo());
            terrenos.add(new Pastizal());
        }
        for(int i = 0; i < 3; i++){
            terrenos.add(new Montaña());
            terrenos.add(new Colina());
        }

        terrenos.add(new Desierto());

        return terrenos;
    }

    //Urgente consultar si no rompe con DIP.
    public abstract void darRecurso(Vertice vertice);

}
