package Tablero;

import Errores.VerticeOcupadoPorCiudad;
import Jugador.Jugador;
import Recurso.Piedra;
import Tablero.Vertice.Estructura.Ciudad;
import Tablero.Vertice.Estructura.Poblado;
import Tablero.Vertice.Estructura.PobladoInicial;
import Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class EstructuraTest {

    @Test
    public void test01LosPobladosDan1Recurso() {

        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("jaime");
        PobladoInicial poblado = new PobladoInicial(jugador);
        Piedra piedrita = new Piedra();

        vertice.ubicarEstructura(poblado);
        vertice.darRecurso(piedrita);

        Assertions.assertEquals(jugador.cantidadCartas(), 1);

    }

    @Test
    public void test02LasCiudadesDan2Recursos() {

        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("jaime");
        Piedra piedrita = new Piedra();

        vertice.ubicarEstructura(new PobladoInicial(jugador));
        vertice.ubicarEstructura(new Ciudad(jugador));
        vertice.darRecurso(piedrita);

        Assertions.assertEquals(jugador.cantidadCartas(), 2);

    }

    @Test
    public void test03LosPobladosAumentanElPuntaje() {
        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("Ariel");
        PobladoInicial poblado = new PobladoInicial(jugador);

        vertice.ubicarEstructura(poblado);

        Assertions.assertEquals(jugador.calcularPuntaje(), 1);
    }

    @Test
    public void test04LasCiudadesAumentanElPuntaje() {
        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("Ariel");
        PobladoInicial poblado = new PobladoInicial(jugador);
        Ciudad ciudad = new Ciudad(jugador);

        vertice.ubicarEstructura(poblado);
        vertice.ubicarEstructura(ciudad);

        Assertions.assertEquals(jugador.calcularPuntaje(), 2);
    }

    @Test
    public void test05NoPuedoPonerUnaCiudadSobreUnaCiudad() {
        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador("Ariel");
        PobladoInicial poblado = new PobladoInicial(jugador);
        Ciudad ciudad = new Ciudad(jugador);
        Ciudad ciudad2 = new Ciudad(jugador);

        vertice.ubicarEstructura(poblado);
        vertice.ubicarEstructura(ciudad);

        Assertions.assertThrows(VerticeOcupadoPorCiudad.class, ()->{vertice.ubicarEstructura(ciudad2);});
    }
}
