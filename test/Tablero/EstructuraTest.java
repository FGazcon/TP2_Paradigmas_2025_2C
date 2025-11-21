package Tablero;

import Jugador.Jugador;
import Recurso.Piedra;
import Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class EstructuraTest {

    @Test
    public void testEstructura() {

        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("jaime");
        Piedra piedrita = new Piedra();

        vertice.ubicarPoblado(jugador);
        vertice.darRecurso(piedrita);

        Assertions.assertEquals(jugador.cantidadCartas(), 1);

    }

    @Test
    public void testCiudadDa2() {

        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("jaime");
        Piedra piedrita = new Piedra();

        vertice.ubicarPoblado(jugador);
        vertice.ubicarCiudad(jugador);
        vertice.darRecurso(piedrita);

        Assertions.assertEquals(jugador.cantidadCartas(), 2);

    }

}
