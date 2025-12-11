package Comercio;

import model.Banco.Banco;
import model.Comercio.DosPorUno;
import model.Comercio.TresPorUno;
import model.Recurso.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.Jugador.Jugador;

public class ComercioTest {

    @Test
    public void test01ComercioCuatroPorUnoFunciona() {
        Jugador jugador = new Jugador("Jugador", new Banco());

        Madera madera = new Madera();
        Trigo trigo = new Trigo();

        jugador.pedirAlBanco(madera, 4);

        jugador.comerciarConBanco(madera, 4, trigo);

        Assertions.assertTrue(jugador.tieneAlMenos(trigo, 1));
    }

    @Test
    public void test02ComercioTresPorUnoFunciona() {
        Banco banco = new Banco();

        Jugador jugador = new Jugador("Jugador", banco);
        jugador.darReglaA(new Oveja(), new TresPorUno());

        Oveja oveja = new Oveja();
        Piedra piedra = new Piedra();

        jugador.pedirAlBanco(oveja, 3);

        jugador.comerciarConBanco(oveja, 3, piedra);

        Assertions.assertTrue(jugador.tieneAlMenos(piedra, 1));
    }

    @Test
    public void test03ComercioDosPorUnoFunciona() {
        Banco banco = new Banco();

        Jugador jugador = new Jugador("Jugador", banco);
        jugador.darReglaA(new Madera(), new DosPorUno());

        Madera madera = new Madera(2);
        Oveja oveja = new Oveja(1);

        jugador.pedirAlBanco(madera, 2);

        jugador.comerciarConBanco(madera, 2, oveja);

        Assertions.assertTrue(jugador.tieneAlMenos(oveja, 1));
    }

    @Test
    public void test04ElComercioNoseEfectuaSiFaltanRecursos(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Sapardo", banco);
        Madera madera = new Madera(4);
        madera.cambiarRegla(new TresPorUno());
        jugador.sumarRecurso(madera, 4);

        jugador.comerciarConBanco(madera, 2, new Ladrillo());

        Assertions.assertFalse(jugador.tieneAlMenos(new Ladrillo(), 1));
    }

    @Test
    public void test05ElComercioQuitaLosRecursosEnviados(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Sapardo", banco);
        Madera madera = new Madera(4);
        jugador.sumarRecurso(madera, 4);

        jugador.comerciarConBanco(madera, 4, new Ladrillo());

        Assertions.assertFalse(jugador.tieneAlMenos(new Madera(), 1));
    }

}