package Dados;

import java.util.Random;

public class Dados {

    private Random random;

    public Dados(){
        random = new Random();
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
