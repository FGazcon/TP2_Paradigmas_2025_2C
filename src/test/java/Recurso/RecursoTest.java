package Recurso;

import model.Recurso.Madera;
import model.Recurso.Recurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecursoTest {

    @Test
    public void test01RecursoRetornaCorretamenteSuCantidad() {
        Recurso madera = new Madera(4);

        Assertions.assertEquals(4, madera.getCantidad());
    }

    @Test
    public void test02RecursoAumentaCorrectamenteSuCanditad() {
        Recurso madera = new Madera(4);

        madera.sumar(1);

        Assertions.assertEquals(5, madera.getCantidad());
    }

    @Test
    public void test03RecursoReduceCorrectamenteSuCantidad() {
        Recurso madera = new Madera(4);

        madera.descartar(1);

        Assertions.assertEquals(3, madera.getCantidad());
    }

    @Test
    public void test04RecursoComparaSiTieneAlMenosXCantidad() {
        Recurso madera = new Madera(4);

        Assertions.assertTrue(madera.tieneAlMenos(3));
    }

    @Test
    public void test05RecursoComparaSiTieneAlMenosXCantdadYFalla() {
        Recurso madera = new Madera(4);

        Assertions.assertFalse(madera.tieneAlMenos(5));
    }

}
