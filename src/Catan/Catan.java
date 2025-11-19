package Catan;

import Banco.Banco;
import Dados.Dados;
import Errores.VerticeNoVacio;
import Jugador.Jugador;
import Ladron.Ladron;
import Tablero.Tablero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catan {

    private Banco banco;
    private List<Jugador> jugadores;
    private Tablero tablero;
    private Dados dados;
    private Ladron ladron;

    public Catan() {

        this.banco = new Banco();
        this.jugadores = new ArrayList<Jugador>();
        this.tablero = new Tablero();
        this.dados = new Dados();
        this.ladron = new Ladron(null);

    }

    public void prepararJugadores(){
        this.jugadores = PreparadoDeJugadores.prepararJugadores(this.banco);
    }

    public int intentarUbicarPoblado(Jugador jugador, int numeroDeVerice){

        //Para que tenga sentido, la eleccion del numero de vertice tiene que estar aca.

        try{
            this.tablero.ubicarPoblado(jugador, numeroDeVerice);
        } catch (VerticeNoVacio e) {
            System.out.println("No se puede ubicar un vertice");
            return this.intentarUbicarPoblado(jugador, numeroDeVerice);
        }
        return numeroDeVerice;
    }

    public void primeraEtapa(){

        int numeroDeVertice = 4;
        for(Jugador jugador: this.jugadores){
            intentarUbicarPoblado(jugador, numeroDeVertice);
            numeroDeVertice+=30;
        }

        for(Jugador jugador: this.jugadores){
            int verticeSegundoPoblado = intentarUbicarPoblado(jugador, numeroDeVertice);
            this.tablero.activarParaSegundoPoblado(verticeSegundoPoblado);
            numeroDeVertice+=4;
        }

    }

}
