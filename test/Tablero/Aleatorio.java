package Tablero;

import Recurso.Recurso;
import Terreno.Productor.Bosque;
import Terreno.Terreno;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Aleatorio {



/*
        @Test
        public void testAsignacionTerrenosYNumeros() {

            // Mockeamos Terrenos
            List<Terreno> terrenosMock = new ArrayList<>();
            for (int i = 0; i < 19; i++) {
                Terreno t = mock(Terreno.class);
                when(t.getNombre()).thenReturn("Terreno" + i);
                terrenosMock.add(t);
            }

            // Creamos lista de números predecible
            List<Integer> numerosMock = new ArrayList<>();
            for (int i = 2; i <= 12; i++) { // ejemplo simplificado
                numerosMock.add(i);
            }

            // Mockeamos método generarNumerosMezclados para devolver lista predecible
            MezcladorTablero mezclador = spy(MezcladorTablero.class);
            doReturn(numerosMock).when(mezclador).generarNumerosMezclados();

            // Vertices predecibles
            Vertice[][] vertices = Vertice.generarVertices();

            // Ejecutamos
            List<Hexagono> hexagonos = MezcladorTablero.mezclarNumerosYHexagonos(vertices, terrenosMock);

            // Verificaciones
            for (int i = 0; i < hexagonos.size(); i++) {
                Hexagono h = hexagonos.get(i);
                System.out.println(h.getTerreno().getNombre() + " - " + h.getNumero());
                assertNotNull(h.getTerreno());
                assertNotNull(h.getNumero());
            }

            // Verificar que se usaron exactamente 19 terrenos
            assertEquals(19, hexagonos.size());
        }

*/

    @Test
    public void testMismoHexagonoDaMaderaSiempre(){
        List <Terreno> terrenosMock = mock(List.class);
        when(terrenosMock.removeFirst()).thenReturn(new Bosque());
 //       terrenosMock = Terreno.generar19Terrenos();
        List<Hexagono> hexagonosAColocar = new ArrayList<Hexagono>();
        hexagonosAColocar = Hexagono.generar19Hexagonos(terrenosMock);
        Recurso recurso;
        Tablero tablero = new Tablero(hexagonosAColocar);
       // recurso = tablero.activarHexagono(4);
       // assertInstanceOf(Madera.class, recurso);

    }


    @Test
    public void testNumerosAleatoreos(){

        List<Integer> numeros = new ArrayList<Integer>();
        List <Integer> numerosMock = mock(List.class);
        when(numerosMock.removeFirst()).thenReturn(1);
        numeros = MezcladorTablero.generarNumerosMezclados();
        numerosMock = MezcladorTablero.generarNumerosMezclados();


        assertNotEquals(numerosMock.removeFirst(), numeros.removeFirst());

    }
    @Test
    public void testTerrenosAleatoreos(){

        List<Terreno> terrenos = new ArrayList<Terreno>();
        List <Terreno> terrenosMock = mock(List.class);
        when(terrenosMock.removeFirst()).thenReturn(null);
        terrenos= Terreno.generar19Terrenos();
        terrenosMock = Terreno.generar19Terrenos();


        assertNotEquals(terrenosMock.removeFirst(), terrenos.removeFirst());

    }


}
