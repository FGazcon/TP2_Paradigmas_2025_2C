package Tablero;

import java.util.Random;

public class RandomizadorFichaNumero {

    private Random random;

    public RandomizadorFichaNumero(Random random) {
        this.random = random;
    }

    public int[] generarNumeros() {

        int[] arrayNumero = {2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12};
        for (int i = arrayNumero.length - 1; i > 0; i--) {
            int j = this.random.nextInt(i + 1);
            int temp = arrayNumero[i];
            arrayNumero[i] = arrayNumero[j];
            arrayNumero[j] = temp;
        }

        return arrayNumero;

    }

    public FichaNumero[] mezclarFichas(FichaNumero[] fichas) {

        for (int i = fichas.length - 1; i > 0; i--) {
            int j = this.random.nextInt(i + 1);
            FichaNumero temp = fichas[i];
            fichas[i] = fichas[j];
            fichas[j] = temp;
        }

        return fichas;
    }
}