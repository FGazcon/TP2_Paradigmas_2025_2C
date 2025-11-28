package Recurso;

import Jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecursoTest {

    @Test
    public void test01ConstruyoCarreteraConMaderaYLadrillo() {
        Recurso recurso1 = new Ladrillo();
        Recurso recurso2 = new Madera();
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recurso1);
        recursos.add(recurso2);
        Jugador jugador = new Jugador("Joseph");
        jugador.pedirAlBanco(recurso1);
        jugador.pedirAlBanco(recurso2);

        recurso1.construirCarretera(recursos,jugador);
        assertFalse(jugador.tiene(recurso1));
        assertFalse(jugador.tiene(recurso2));
    }
    @Test
    public void test02ConstruyoPobladoConTodosLosRecursosEnMano() {
        Recurso recurso1 = new Ladrillo();
        Recurso recurso2 = new Madera();
        Recurso recurso3 = new Oveja();
        Recurso recurso4 = new Trigo();
        Recurso recurso5 = new Piedra();

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recurso1);
        recursos.add(recurso2);
        recursos.add(recurso3);
        recursos.add(recurso4);
        recursos.add(recurso5);
        Jugador jugador = new Jugador("Joseph");
        jugador.pedirAlBanco(recurso1);
        jugador.pedirAlBanco(recurso2);
        jugador.pedirAlBanco(recurso3);
        jugador.pedirAlBanco(recurso4);
        jugador.pedirAlBanco(recurso5);

        recurso1.construirPoblado(recursos,jugador);
        assertFalse(jugador.tiene(recurso1));
        assertFalse(jugador.tiene(recurso2));
        assertFalse(jugador.tiene(recurso3));
        assertFalse(jugador.tiene(recurso4));
        assertTrue(jugador.tiene(recurso5));
    }
    @Test
    public void test02ConstruyoPobladoSinTodosLosRecursosNecesarios() {
        Recurso recurso1 = new Ladrillo();
        Recurso recurso2 = new Madera();
        Recurso recurso3 = new Oveja();
        Recurso recurso4 = new Piedra();

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recurso1);
        recursos.add(recurso2);
        recursos.add(recurso3);
        recursos.add(recurso4);
        Jugador jugador = new Jugador("Joseph");
        jugador.pedirAlBanco(recurso1);
        jugador.pedirAlBanco(recurso2);
        jugador.pedirAlBanco(recurso3);
        jugador.pedirAlBanco(recurso4);

        recurso1.construirPoblado(recursos,jugador);
        assertTrue(jugador.tiene(recurso1));
        assertTrue(jugador.tiene(recurso2));
        assertTrue(jugador.tiene(recurso3));
        assertTrue(jugador.tiene(recurso4));
    }
}
