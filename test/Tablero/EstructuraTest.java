package Tablero;

import Jugador.Jugador;
import Recurso.Piedra;
import Tablero.Vertice.Estructura.Ciudad;
import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Estructura.Poblado;
import Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class EstructuraTest {

    @Test
    public void testEstructura() {

        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("jaime");
        Poblado poblado = new Poblado(jugador);
        Piedra piedrita = new Piedra();

        vertice.ubicarEstructura(poblado);
        vertice.darRecurso(piedrita);

        Assertions.assertEquals(jugador.cantidadCartas(), 1);

    }

    @Test
    public void testCiudadDa2() {

        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("jaime");
        Piedra piedrita = new Piedra();

        vertice.ubicarEstructura(new Poblado(jugador));
        vertice.ubicarEstructura(new Ciudad(jugador));
        vertice.darRecurso(piedrita);

        Assertions.assertEquals(jugador.cantidadCartas(), 2);

    }

}
