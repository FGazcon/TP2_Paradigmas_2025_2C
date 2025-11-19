package Dados;

import java.util.Random;

public class Dados {

    private Random random;
    private static Dados dados = new Dados();

    public Dados(){
        random = new Random();
    }

    public static Dados getDados(){
        return dados;
    }

    public int tirarDados(){
        int dado1 = tirarDado();
        int dado2 = tirarDado();

        System.out.println(dado1 + dado2);

        return dado1 + dado2;
    }

    private int tirarDado(){
        return this.random.nextInt(6) + 1;
    }

}
