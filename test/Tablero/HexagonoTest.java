package Tablero;

import Errores.HexagonoBajoAsalto;
import Errores.VerticeNoVacio;
import Jugador.Jugador;
import Ladron.Ladron;
import Recurso.Madera;
import Terreno.Productor.Bosque;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class HexagonoTest {

    @Test
    public void test01HexagonoBLoqueadoNoProduce(){

        Bosque bosque = mock(Bosque.class);
        Hexagono hexagono = new Hexagono(bosque, 10);

        hexagono.recibirLadron();

        Assertions.assertThrows(HexagonoBajoAsalto.class, ()->{hexagono.activarHexagonoNumero(10);});

    }

}
