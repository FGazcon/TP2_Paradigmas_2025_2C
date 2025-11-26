package Jugador;

import Produccion.MazoProduccion;
import Recurso.Madera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JugadorTest {

    @Test
    public void test02JugadorDescartaMitad(){

        MazoProduccion mazoProduccion = new MazoProduccion();
        mazoProduccion.crearMazoParaBanco();
        Jugador jugador = new Jugador("Jeferson");
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());



        jugador.descartarMitad();
        int cantidadCartasRecibidas = jugador.cantidadCartas();
        int cantidadCartasJugadorEsperado = 5;

        //carta = mazoProduccion.recibirRecurso(RecursoFactory.crearRecurso("Madera"));
        //Carta cartaEsperada = new Carta("Madera");

        Assertions.assertEquals(cantidadCartasJugadorEsperado, cantidadCartasRecibidas);

    }

}
