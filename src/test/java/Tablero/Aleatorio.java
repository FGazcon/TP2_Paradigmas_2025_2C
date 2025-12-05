package Tablero;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class Aleatorio {



/*
        @Test
        public void testAsignacionTerrenosYNumeros() {

            // Mockeamos Terrenos
            List<java.Terreno> terrenosMock = new ArrayList<>();
            for (int i = 0; i < 19; i++) {
                java.Terreno t = mock(java.Terreno.class);
                when(t.getNombre()).thenReturn("java.Terreno" + i);
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


    @Test
    public void testMismoHexagonoDaMaderaSiempre(){
        List <java.Terreno> terrenosMock = mock(List.class);
        when(terrenosMock.removeFirst()).thenReturn(new Bosque());
        //       terrenosMock = java.Terreno.generar19Terrenos();
        List<Hexagono> hexagonosAColocar = new ArrayList<Hexagono>();
        hexagonosAColocar = Hexagono.generar19Hexagonos();
        java.Recurso recurso;
        java.Tablero tablero = new java.Tablero();
        // recurso = tablero.activarHexagono(4);
        // assertInstanceOf(Madera.class, recurso);

    }


    @Test
    public void testNumerosAleatoreos(){

        List<Integer> numeros = new ArrayList<Integer>();
        List <Integer> numerosMock = mock(List.class);
        when(numerosMock.removeFirst()).thenReturn(1);
        numeros = Factory_MapaBasico.generarNumerosMezclados();
        numerosMock = Factory_MapaBasico.generarNumerosMezclados();

        assertNotEquals(numerosMock.removeFirst(), numeros.removeFirst());

    }


    //Revisar este test despues de factory.
    @Test
    public void testTerrenosAleatoreos(){

        List<java.Terreno> terrenos = new ArrayList<java.Terreno>();
        List <java.Terreno> terrenosMock = mock(List.class);
        when(terrenosMock.removeFirst()).thenReturn(null);
        terrenos= java.Terreno.generar19Terrenos();
        terrenosMock = java.Terreno.generar19Terrenos();

        assertNotEquals(terrenosMock.removeFirst(), terrenos.removeFirst());

    }


 */

}