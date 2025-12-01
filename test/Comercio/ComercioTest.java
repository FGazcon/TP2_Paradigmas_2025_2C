package Comercio;

import Jugador.Jugador;
import Recurso.Madera;
import Recurso.Trigo;
import Recurso.Oveja;
import Recurso.Piedra;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComercioTest {
    /*
    @Test
    public void test01ComercioCuatroPorUnoFunciona() {
        Jugador jugador = new Jugador("Jugador");
        jugador.asignarReglaDeComercio(new CuatroPorUno());

        Madera madera = new Madera();
        Trigo trigo = new Trigo();

        jugador.pedirAlBanco(madera);
        jugador.pedirAlBanco(madera);
        jugador.pedirAlBanco(madera);
        jugador.pedirAlBanco(madera);

        boolean tradeo = jugador.comerciarEnPuerto(madera, trigo);

        Assertions.assertTrue(tradeo);
        Assertions.assertTrue(jugador.tiene(trigo));
        Assertions.assertFalse(jugador.tieneCantidadDe(madera, 1));
    }

    @Test
    public void test02ComercioTresPorUnoFunciona() {
        Jugador jugador = new Jugador("Jugador");
        jugador.asignarReglaDeComercio(new TresPorUno());

        Oveja oveja = new Oveja();
        Piedra piedra = new Piedra();

        jugador.pedirAlBanco(oveja);
        jugador.pedirAlBanco(oveja);
        jugador.pedirAlBanco(oveja);

        boolean tradeo = jugador.comerciarEnPuerto(oveja, piedra);

        Assertions.assertTrue(tradeo);
        Assertions.assertTrue(jugador.tiene(piedra));
    }

    @Test
    public void test03ComercioDosPorUnoFunciona() {
        Jugador jugador = new Jugador("Jugador");
        jugador.asignarReglaDeComercio(new DosPorUno(Madera.class));

        Madera madera = new Madera();
        Oveja oveja = new Oveja();

        jugador.pedirAlBanco(madera);
        jugador.pedirAlBanco(madera);

        boolean tradeo = jugador.comerciarEnPuerto(madera, oveja);

        Assertions.assertTrue(tradeo);
        Assertions.assertTrue(jugador.tiene(oveja));
    }

    @Test
    public void test04SeTrataDeNegociarConTresPorUnoPeroNoHaySuficientesCartas() {
        Jugador jugador = new Jugador("Jugador");
        jugador.asignarReglaDeComercio(new TresPorUno());

        Piedra piedra = new Piedra();
        Trigo trigo = new Trigo();

        jugador.pedirAlBanco(piedra);
        jugador.pedirAlBanco(piedra);

        boolean tradeo = jugador.comerciarEnPuerto(piedra, trigo);

        Assertions.assertFalse(tradeo);
    }

    @Test
    public void test05PuertoDosPorUnoPeroRecursoIncorrecto() {
        Jugador jugador = new Jugador("Jugador");
        jugador.asignarReglaDeComercio(new DosPorUno(Trigo.class));

        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());

        boolean tradeo = jugador.comerciarEnPuerto(new Madera(), new Piedra());

        Assertions.assertFalse(tradeo);
    }

    @Test
    public void test06SinReglaNoHayComercio() {
        Jugador jugador = new Jugador("Jugador");

        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());
        jugador.pedirAlBanco(new Madera());

        boolean tradeo = jugador.comerciarEnPuerto(new Madera(), new Trigo());

        Assertions.assertFalse(tradeo);
    }
    */
}