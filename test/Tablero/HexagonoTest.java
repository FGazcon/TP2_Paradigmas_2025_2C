package Tablero;

import model.Errores.HexagonoBajoAsalto;
import model.Jugador.Jugador;
import model.Tablero.Hexagono;
import model.Tablero.Vertice.Vertice;
import model.Terreno.Productor.Bosque;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class HexagonoTest {

    /*
    @Test
    public void test01HexagonoBLoqueadoNoProduce(){

        Bosque bosque = mock(Bosque.class);
        Hexagono hexagono = new Hexagono(bosque, 10);
        Vertice[] vertices = {new Vertice(), new Vertice()};
        hexagono.setVertices(vertices);
        Jugador jugador = mock(Jugador.class);

        hexagono.recibirLadron(jugador);

        Assertions.assertThrows(HexagonoBajoAsalto.class, ()->{hexagono.activarHexagonoParaNumero(10);});
    }
    */

}
