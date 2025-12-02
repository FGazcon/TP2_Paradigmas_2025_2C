package model.Terreno.Productor;

import model.Terreno.Terreno;

import java.util.ArrayList;
import java.util.List;

public abstract class Productor extends Terreno {

    public static List<Terreno> generar18TerrenosProductores(){

        List<Terreno> productores = new ArrayList<Terreno>();

        for(int i = 0; i < 4; i++){
            productores.add(new Bosque());
            productores.add(new Campo());
            productores.add(new Pastizal());
        }
        for(int i = 0; i < 3; i++){
            productores.add(new Montaña());
            productores.add(new Colina());
        }

        return productores;

    }

}
