package Catan;

import Banco.Banco;
import Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PreparadoDeJugadores {

    public static List<Jugador> prepararJugadores(Banco banco){

        List<Jugador> jugadores = new ArrayList<>();

        //Aca ira la logica de que los jugadores elijan color, nombre, etc.
        //Demo:
        Scanner sc = new Scanner(System.in);  // Crear Scanner para leer desde la terminal

        System.out.print("Ingresa un nombre: ");
        String nombre = sc.nextLine();
        Jugador jugador = new Jugador(nombre, banco);
        jugadores.add(jugador);


        return jugadores;

    }

}
