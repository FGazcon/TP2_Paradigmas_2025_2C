package Ladron;

public class Ladron {

    private int numeroDeHexagono;
    private static Ladron ladron =  new Ladron();

    Ladron() {
        this.numeroDeHexagono = 0;
    }

    public static Ladron getLadron(){
        return ladron;
    }

    public void ubicarseEn(int numeroDeHexagono){
        this.numeroDeHexagono = numeroDeHexagono;
    }

    //public Ladron();
//devuelve el hexagono anterior
    public int moverLadron(int numeroDeHexagono){
        int aux = this.numeroDeHexagono;
        this.numeroDeHexagono = numeroDeHexagono;
        return aux;
    }

}
