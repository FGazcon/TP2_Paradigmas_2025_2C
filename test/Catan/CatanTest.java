package Catan;

import Banco.Banco;
import Dados.Dados;
import Jugador.Jugador;
import org.junit.jupiter.api.Test;
import java.util.*;


import java.util.ArrayList;



public class CatanTest {

    @Test
    public void test01LosJugadoresRecibenRecursosIniciales(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar");
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores,banco);
        int numeroDeVertice = 20;
        catan.segundaEleccion(jugador,numeroDeVertice); //esto deberia darle los recursos al jugador
        //aca deberia implementar para saber que recursos gano el jugador


        // int recursosObtenidos = jugador.cantidadCartas();

        //en el assertion comparo si los recursos que gano son los que se esperaba que gane. asi parece
        //  Assertions.assertNotEquals(0,recursosObtenidos);

    }
    @Test
    public void test02SeRecibenLosRecursosCorrectamenteSegunElNumero(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar");
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores,banco);

        int numeroSacado;
        Dados dados = new Dados();
        numeroSacado = dados.tirarDados();
        catan.lanzamientoDeDados(numeroSacado);

        //int recursosObtenidos = jugador.cantidadCartas();


        // Assertions.assertNotEquals(0,recursosObtenidos);

    }
    //Verificar que el Terreno bajo el Ladrón no produzca recursos.
    //      ● Verificar que si un jugador tiene más de 7 cartas, descarte correctamente la mitad,
    //redondeando hacia abajo, al lanzar un 7.
    //      ● Verificar que el jugador activo pueda mover el Ladrón y robar una carta aleatoria
    //de un jugador adyacente a la nueva ubicación.

    @Test
    public void test03HayLadronNoSaleRecurso(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar");
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores,banco);
        //Ladron ladron = new Ladron();
        catan.activarLadron(jugador);

    }
/*
    @Test
    public void test04JugadorPierdeLaMitadDeLasCartas(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar", banco);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        jugador.pedirAlBanco(MADERA);
        jugador.pedirAlBanco(MADERA);
        jugador.pedirAlBanco(MADERA);
        jugador.pedirAlBanco(MADERA);
        jugador.pedirAlBanco(MADERA);
        jugador.pedirAlBanco(MADERA);
        jugador.pedirAlBanco(MADERA);
        jugador.pedirAlBanco(MADERA);

        Catan catan = new Catan(jugadores,banco);
        catan.descarte();


        int recursosObtenidos = jugador.cantidadCartas();


        Assertions.assertEquals(4,recursosObtenidos);

    }
*/
/*
    public class MezcladorTableroTest {

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
    }
*/

    @Test
    public void testMismoHexagonoDaMaderaSiempre(){

    }


    @Test
    public void testNumerosAleatorios(){


    }
}