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
        Estructura estructura = mock(Estructura.class);

        vertice.bloquearse();

        Assertions.assertThrows(VerticeNoVacio.class, ()->{vertice.ubicarEstructura(estructura);});

    }

    @Test void test02VerticeRespetaReglaDeDistanciasAlUbicarPobladoInicial(){

        Vertice vertice = new Vertice();
        Estructura estructura = mock(Estructura.class);

        vertice.ubicarEstructura(estructura);

        Assertions.assertThrows(VerticeNoVacio.class, ()->{vertice.ubicarEstructura(estructura);});

    }

}