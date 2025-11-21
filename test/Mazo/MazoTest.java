package Mazo;

import Produccion.Carta;
import Produccion.MazoProduccion;
import Recurso.RecursoFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class MazoTest {

    @Test
    public void testAgregarCartas(){

        MazoProduccion mazoProduccion = new MazoProduccion();
        mazoProduccion.crearMazoParaBanco();

        Carta carta = new Carta();
        carta = mazoProduccion.recibirRecurso(RecursoFactory.crearRecurso("Madera"));
        Carta cartaEsperada = new Carta("Madera");

        //Fijarse factory recursos

        assertInstanceOf(Carta.class, carta);

    }
}