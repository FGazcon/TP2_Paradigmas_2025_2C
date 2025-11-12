package Tablero;

import java.util.Random;

public class RandomizadorNumeros {

    public static int[] generarNumeros() {

        Random rnd = new Random();

        int[] arrayNumero = {2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12};
        for (int i = arrayNumero.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int temp = arrayNumero[i];
            arrayNumero[i] = arrayNumero[j];
            arrayNumero[j] = temp;
        }

        return arrayNumero;

    }

    public static FichaNumero[] mezclarFichas(FichaNumero[] fichas) {
        Random rnd = new Random();

        for (int i = fichas.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            FichaNumero temp = fichas[i];
            fichas[i] = fichas[j];
            fichas[j] = temp;
        }

        return fichas;
    }
}