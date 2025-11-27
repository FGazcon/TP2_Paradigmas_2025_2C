package Jugador;

import Produccion.MazoProduccion;
import Recurso.Madera;
import Recurso.Oveja;
import Recurso.Piedra;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void test04JugadorPuedeComerciarConOtro() {
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");

        Madera m = new Madera();
        Oveja o = new Oveja();

        jugador1.pedirAlBanco(m);
        jugador2.pedirAlBanco(o);

        boolean tradeo = jugador1.comerciarConJugador(jugador2, List.of(m), List.of(o));

        Assertions.assertTrue(tradeo);
    }

    @Test
    public void test05JugadorPierdeSuRecurso() {
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");

        Madera m = new Madera();
        Oveja o = new Oveja();

        jugador1.pedirAlBanco(m);
        jugador2.pedirAlBanco(o);

        jugador1.comerciarConJugador(jugador2, List.of(m), List.of(o));

        Assertions.assertFalse(jugador1.tiene(m));
    }

    @Test
    public void test06JugadorRecibeRecurso() {
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");

        Madera m = new Madera();
        Oveja o = new Oveja();

        jugador1.pedirAlBanco(m);
        jugador2.pedirAlBanco(o);

        jugador1.comerciarConJugador(jugador2, List.of(m), List.of(o));

        Assertions.assertTrue(jugador2.tiene(m));
    }

    @Test
    public void test07JugadorPuedeComerciar4a1ConBanco() {
        Jugador jugador = new Jugador("Jugador");

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        Madera madera3 = new Madera();
        Madera madera4 = new Madera();
        Piedra piedra = new Piedra();

        jugador.pedirAlBanco(madera1);
        jugador.pedirAlBanco(madera2);
        jugador.pedirAlBanco(madera3);
        jugador.pedirAlBanco(madera4);

        boolean tradeo = jugador.comerciarConBanco(new Madera(), piedra);

        Assertions.assertTrue(tradeo);
        Assertions.assertTrue(jugador.tiene(piedra));
    }

    @Test
    public void test08JugadorNoPuedeComerciarSiNoTiene4Iguales() {
        Jugador jugador = new Jugador("Jugador");

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        Madera madera3 = new Madera();

        jugador.pedirAlBanco(madera1);
        jugador.pedirAlBanco(madera2);
        jugador.pedirAlBanco(madera3);

        boolean tradeo = jugador.comerciarConBanco(new Madera(), new Piedra());

        Assertions.assertFalse(tradeo);
        Assertions.assertFalse(jugador.tiene(new Piedra()));
    }

    @Test
    public void test09JugadorEntregaExactamenteCuatroRecursosAlBanco() {
        Jugador jugador = new Jugador("Jugador");

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        Madera madera3 = new Madera();
        Madera madera4 = new Madera();
        Madera madera5 = new Madera();

        jugador.pedirAlBanco(madera1);
        jugador.pedirAlBanco(madera2);
        jugador.pedirAlBanco(madera3);
        jugador.pedirAlBanco(madera4);
        jugador.pedirAlBanco(madera5);

        jugador.comerciarConBanco(new Madera(), new Piedra());

        // Va a tener la madera que sobró y la piedra que recibió del tradeo

        Assertions.assertEquals(2, jugador.cantidadCartas());
    }



}
