package Catan;

import Banco.Banco;
import Dados.Dados;
import Jugador.Jugador;
import Recurso.Madera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;


import java.util.ArrayList;



public class CatanTest {

    @Test
    public void test01LosJugadoresRecibenRecursosIniciales(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar");
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores);
        int numeroDeVertice = 20;
        catan.segundaEleccion(jugador,numeroDeVertice); //esto deberia darle los recursos al jugador
        //aca deberia implementar para saber que recursos gano el jugador


        int recursosObtenidos = jugador.cantidadCartas();

        //en el assertion comparo si los recursos que gano son los que se esperaba que gane. asi parece
         Assertions.assertNotEquals(0,recursosObtenidos);

    }

    //Verificar que el Terreno bajo el Ladrón no produzca recursos.
    //      ● Verificar que si un jugador tiene más de 7 cartas, descarte correctamente la mitad,
    //redondeando hacia abajo, al lanzar un 7.
    //      ● Verificar que el jugador activo pueda mover el Ladrón y robar una carta aleatoria
    //de un jugador adyacente a la nueva ubicación.


    @Test
    public void test02JugadorPierdeLaMitadDeLasCartas(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar");
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());

        Catan catan = new Catan(jugadores);
        jugador.descartarMitad();


        int recursosObtenidos = jugador.cantidadCartas();


        Assertions.assertEquals(4,recursosObtenidos);

    }

}