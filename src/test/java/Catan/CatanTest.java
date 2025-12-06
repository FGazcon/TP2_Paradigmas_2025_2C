package Catan;

import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.Turno;
import model.Catan.TurnoGeneral;
import model.Dados.Dados;
import model.Jugador.Jugador;
import model.Recurso.Ladrillo;
import model.Recurso.Madera;
import model.Recurso.Oveja;
import model.Recurso.Trigo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


public class CatanTest {

    @Test
    public void test01LosJugadoresRecibenRecursosIniciales(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar", banco);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores, banco);
        //catan.ejecutarTurnoInicial(30); //esto deberia darle los recursos al jugador
        //aca deberia implementar para saber que recursos gano el jugador


        int recursosObtenidos = jugador.cantidadCartas();

        //en el assertion comparo si los recursos que gano son los que se esperaba que gane. asi parece
         //Assertions.assertNotEquals(0,recursosObtenidos);

    }

    //Verificar que el java.Terreno bajo el Ladrón no produzca recursos.
    //      ● Verificar que si un jugador tiene más de 7 cartas, descarte correctamente la mitad,
    //redondeando hacia abajo, al lanzar un 7.
    //      ● Verificar que el jugador activo pueda mover el Ladrón y robar una carta aleatoria
    //de un jugador adyacente a la nueva ubicación.


    @Test
    public void test02JugadorPierdeLaMitadDeLasCartas(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Wilmar", banco);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(jugador);
        jugador.pedirAlBanco(new Madera(), 8);
        jugador.descartarMitad();


        int recursosObtenidos = jugador.cantidadCartas();


        Assertions.assertEquals(4,recursosObtenidos);

    }

    @Test
    public void test03JugadorCompraUnPoblado(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Williberto", banco);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);

        Catan catan = new Catan(jugadores, banco);

        jugador.pedirAlBanco(new Madera(), 1);
        jugador.pedirAlBanco(new Ladrillo(), 1);
        jugador.pedirAlBanco(new Trigo(), 1);
        jugador.pedirAlBanco(new Oveja(), 1);

        TurnoGeneral turno = new TurnoGeneral(catan, catan.getTablero(), jugador, new Dados());

        //turno.construirPoblado();
    }

    @Test
    public void test04ElCatanGeneraTurnosParaSusJugadores(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Gibby", banco);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores, banco);

        Turno turno = catan.getTurno();

        turno.getJugadorActual();

        Assertions.assertEquals(jugador,turno.getJugadorActual());
    }

    @Test
    public void test05ElPrimerPobladoNoGeneraNada(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Gibby", banco);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores, banco);

        Turno turno = catan.getTurno();

        turno.getJugadorActual();

        turno.construirPoblado(53);

        Assertions.assertEquals(jugador.cantidadCartas(), 0);
    }

    @Test
    public void test06ElSegundoPobladoInicialGeneraRecursos(){
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Gibby", banco);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        Catan catan = new Catan(jugadores, banco);

        catan.terminarTurno();

        Turno turno = catan.getTurno();

        turno.getJugadorActual();

        turno.construirPoblado(53);

        Assertions.assertNotEquals(jugador.cantidadCartas(), 0);
    }


}