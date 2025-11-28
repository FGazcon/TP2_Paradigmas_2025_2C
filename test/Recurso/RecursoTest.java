package Recurso;

import org.junit.jupiter.api.Test;
import Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

public class RecursoTest {

    @Test
    public void test01ConstruyoCarreteraConMaderaYLadrillo() {
        Recurso recurso1 = new Ladrillo();
        Recurso recurso2 = new Madera();
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recurso1);
        recursos.add(recurso2);
        Jugador jugador = new Jugador("Joseph");


       // recurso1.construirCarretera(recursos);
    }
}
