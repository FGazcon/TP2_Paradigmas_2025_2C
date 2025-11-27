package Jugador;

import Produccion.MazoProduccion;
import Recurso.Madera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JugadorTest {

    @Test
    public void test01JugadorLePasaCartaAOtroJugadorAlSerRobado(){

        Jugador jugador = new Jugador("Sapardo");
        Jugador jugador1 = new Jugador("Abuelacho");

        jugador.pedirAlBanco(new Madera());
        jugador.dejarseRobarPorJugador(1, jugador1);

        Assertions.assertEquals(jugador1.cantidadCartas(), 1);
    }

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

        Assertions.assertEquals(cantidadCartasJugadorEsperado, cantidadCartasRecibidas);

    }

    @Test
    public void test03JugadorAumentaSuPuntaje(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarPunto();
        jugador.sumarPunto();

        Assertions.assertEquals(jugador.calcularPuntaje(), 2);
    }

}
