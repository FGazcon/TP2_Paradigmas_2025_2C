package Catan;

import Banco.Banco;
import Dados.Dados;
import Jugador.Jugador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import Tablero.Tablero;
import Tablero.Vertice.Estructura.Poblado;


import java.util.ArrayList;

import static Recurso.Recurso.MADERA;


public class CatanTest {

    @Test
    public void test01LosJugadoresRecibenRecursosIniciales(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar", banco);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores,banco);
        catan.primeraEtapa();

        int recursosObtenidos = jugador.cantidadCartas();


        Assertions.assertNotEquals(0,recursosObtenidos);

    }
    @Test
    public void testUbicacionDePobladoConMockito() {
        Banco banco = new Banco();

        // Usamos spy para mockear solo algunos métodos
        Jugador jugador = Mockito.spy(new Jugador("Wilmar", banco));

        // CONTROLAMOS EL VÉRTICE
        Mockito.doReturn(7).when(jugador).elegirVertice();
        Mockito.doReturn(12).when(jugador).elegirVertice();

        Tablero tablero = Mockito.mock(Tablero.class);

        // Act
        jugador.ubicarPoblado(tablero);
        jugador.ubicarPoblado2(tablero);

        // Assert
        Mockito.verify(tablero).ubicarEstructura(Mockito.any(Poblado.class), Mockito.eq(7));
        Mockito.verify(tablero).ubicarEstructura(Mockito.any(Poblado.class), Mockito.eq(12));
    }
    @Test
    public void test02SeRecibenLosRecursosCorrectamenteSegunElNumero(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar", banco);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores,banco);

        int numeroSacado;
        Dados dados = new Dados();
        numeroSacado = dados.tirarDados();
        catan.lanzamientoDeDados(numeroSacado);

        int recursosObtenidos = jugador.cantidadCartas();


        Assertions.assertNotEquals(0,recursosObtenidos);

    }
    //Verificar que el Terreno bajo el Ladrón no produzca recursos.
      //      ● Verificar que si un jugador tiene más de 7 cartas, descarte correctamente la mitad,
    //redondeando hacia abajo, al lanzar un 7.
      //      ● Verificar que el jugador activo pueda mover el Ladrón y robar una carta aleatoria
    //de un jugador adyacente a la nueva ubicación.

    @Test
    public void test03HayLadronNoSaleRecurso(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar", banco);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores,banco);
        //Ladron ladron = new Ladron();
        catan.activarLadron(jugador);

    }

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
}


