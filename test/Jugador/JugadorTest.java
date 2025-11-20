package Jugador;

import Produccion.MazoProduccion;
import Recurso.RecursoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JugadorTest {

    @Test
    public void test02JugadorDescartaMitad(){

        MazoProduccion mazoProduccion = new MazoProduccion();
        mazoProduccion.crearMazoParaBanco();
        Jugador jugador = new Jugador("Jeferson");
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));

        jugador.descartarMitad();
        int cantidadCartasRecibidas = jugador.cantidadCartas();
        int cantidadCartasJugadorEsperado = 5;

        //carta = mazoProduccion.recibirRecurso(RecursoFactory.crearRecurso("Madera"));
        //Carta cartaEsperada = new Carta("Madera");

        Assertions.assertEquals(cantidadCartasJugadorEsperado, cantidadCartasRecibidas);

    }

}
