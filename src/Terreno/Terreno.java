package Terreno;

import java.util.Random;

public abstract class Terreno {

    public static Terreno[] generar19Terrenos(){

        //Arma un array con todos los Terrenos que se usaran, 18 de produccion, 1 desierto.
        //Luego lo reordena y retorna.

        Terreno[] terrenos = new Terreno[19];

        for (int i = 0; i < 4; i++) {
            terrenos[i] = new Bosque();
        }
        for (int i = 4; i < 8; i++) {
            terrenos[i] = new Campo();
        }
        for (int i = 8; i < 12; i++) {
            terrenos[i] = new Pastizal();
        }
        for (int i = 12; i < 15; i++) {
            terrenos[i] = new Montaña();
        }
        for (int i = 15; i < 18; i++) {
            terrenos[i] = new Colina();
        }
        terrenos[18] = new Desierto();


        for (int i = 0; i < terrenos.length; i++) {
            System.out.println(terrenos[i] + " " + i);
        }

        return terrenos;

    }

}
