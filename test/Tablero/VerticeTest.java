package Tablero;

import Errores.VerticeNoVacio;
import Jugador.Jugador;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VerticeTest {

    @Test void test01VerticeRespetaReglaDeDistanciasAlUbicarPobladoInicial(){

        Vertice vertice = new Vertice();
        Jugador jugador = mock(Jugador.class);

        vertice.bloquearse();

        Assertions.assertThrows(VerticeNoVacio.class, ()->{vertice.ubicarPoblado(jugador);});

    }

    @Test void test02VerticeRespetaReglaDeDistanciasAlUbicarPobladoInicial(){

        Vertice vertice = new Vertice();
        Jugador jugador = mock(Jugador.class);

        vertice.ubicarPoblado(jugador);

        Assertions.assertThrows(VerticeNoVacio.class, ()->{vertice.ubicarPoblado(jugador);});

    }

    @Test void test03VerticeDaElelementoCorrecto(){



    }

}