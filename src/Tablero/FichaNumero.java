package Tablero;

import Terreno.Terreno;

public class FichaNumero {

    private Terreno terreno;
    private int numero;

    public FichaNumero(Terreno terreno,  int numero) {
        this.terreno = terreno;
        this.numero = numero;
    }

    public static FichaNumero[] generar19FichasNumero(){

        FichaNumero[] fichas = new FichaNumero[19];

        Terreno[] terrenos = Terreno.generar19Terrenos();

        int[] arrayNumero = RandomizadorNumeros.generarNumeros();

        for (int i = 0; i < fichas.length - 1; i++){
            fichas[i] = new FichaNumero(terrenos[i], arrayNumero[i]);
        }
        fichas[18] = new FichaNumero(terrenos[18], 7);
        

        return RandomizadorNumeros.mezclarFichas(fichas);

        //Quiza en lugar de llamar dos veces al randomizador, hacer que se llame solo una vez. Consultar con Maia.

    }

    public boolean esDesierto() {
        return this.numero == 7;
    }

}