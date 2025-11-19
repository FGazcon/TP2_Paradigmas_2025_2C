package Ladron;

public class Ladron {

    private int numeroHexagono;

    public Ladron(int numeroHexagono) {
        this.numeroHexagono = numeroHexagono;
    }

    public void moverLadron(int destino){
        this.numeroHexagono = destino;
    }

    public void activarLadron(){
        //Activar Robo general a mas de 7 cartas
    }

}
