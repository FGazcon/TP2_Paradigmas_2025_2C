package Tablero;

import model.Errores.VerticeNoVacio;
import model.Jugador.Jugador;
import model.Tablero.Vertice.Estructura.PobladoInicial;
import model.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class VerticeTest {

    @Test void test01VerticeRespetaReglaDeDistanciasAlUbicarPobladoInicial(){

        Vertice vertice = new Vertice();
        Jugador jugador = mock(Jugador.class);

        vertice.bloquearse();

        Assertions.assertThrows(VerticeNoVacio.class, ()->{vertice.ubicarEstructura(new PobladoInicial(jugador));});

    }

    @Test void test02VerticeRespetaReglaDeDistanciasAlUbicarPobladoInicial(){

        Vertice vertice = new Vertice();
        Jugador jugador = mock(Jugador.class);

        vertice.ubicarEstructura(new PobladoInicial(jugador));

        Assertions.assertThrows(VerticeNoVacio.class, ()->{vertice.ubicarEstructura(new PobladoInicial(jugador));});

    }

    @Test void test03ElVertice(){

        Vertice vertice = new Vertice();
        Jugador jugador = mock(Jugador.class);

        vertice.ubicarEstructura(new PobladoInicial(jugador));

        Assertions.assertEquals(vertice.estructuraEsDe(jugador), true);

    }

}