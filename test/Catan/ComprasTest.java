package Catan;

import com.sun.prism.impl.ps.CachingRoundRectRep;
import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.Turno;
import model.Catan.TurnoGeneral;
import model.Dados.Dados;
import model.Desarrollo.CartasDesarrollo.Caballero;
import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Jugador.Jugador;
import model.Recurso.*;
import model.Tablero.Arista.Carretera;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Poblado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

public class ComprasTest {

    @Test
    public void test01JugadorNoLeAlcanzaUnPoblado(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());

        Poblado poblado = new Poblado(jugador);

        Assertions.assertFalse(poblado.jugadorMePuedePagar());

    }

    @Test
    public void test02JugadorLeAlcanzaParaUnPoblado(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());
        jugador.pedirAlBanco(new Madera(), 1);
        jugador.pedirAlBanco(new Ladrillo(), 1);
        jugador.pedirAlBanco(new Trigo(), 1);
        jugador.pedirAlBanco(new Oveja(), 1);
        Poblado poblado = new Poblado(jugador);

        Assertions.assertTrue(poblado.jugadorMePuedePagar());

    }

    @Test
    public void test03JugadorNoLeAlcanzaParaCiudad(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());

        Ciudad poblado = new Ciudad(jugador);

        Assertions.assertFalse(poblado.jugadorMePuedePagar());

    }

    @Test
    public void test04JugadorLeAncanzaParaciudad(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());
        jugador.pedirAlBanco(new Piedra(), 3);
        jugador.pedirAlBanco(new Trigo(), 2);
        Ciudad ciudad = new Ciudad(jugador);

        Assertions.assertTrue(ciudad.jugadorMePuedePagar());

    }

    @Test
    public void test05JugadorNoPuedePagarDesarrollo(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());

        CartaDesarrollo desa = new Caballero();

        Assertions.assertFalse(desa.jugadorMePuedePagar(jugador));

    }

    @Test
    public void test06JugadorLeAncanzaParaDesarrollo(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());
        jugador.pedirAlBanco(new Piedra(), 1);
        jugador.pedirAlBanco(new Trigo(), 1);
        jugador.pedirAlBanco(new Oveja(), 1);
        CartaDesarrollo desa = new Caballero();

        Assertions.assertTrue(desa.jugadorMePuedePagar(jugador));

    }

    @Test
    public void test07JugadorNoPuedePagarCamino(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());

        Carretera carretera = new Carretera(jugador);

        Assertions.assertFalse(carretera.jugadorMePuedePagar());

    }

    @Test
    public void test08JugadoruedePagarCamino(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());
        jugador.pedirAlBanco(new Madera(), 1);
        jugador.pedirAlBanco(new Ladrillo(), 1);
        Carretera carretera = new Carretera(jugador);

        Assertions.assertTrue(carretera.jugadorMePuedePagar());

    }

    @Test
    public void test09JugadorPagaLoQueCompra(){
        Jugador jugador = new Jugador("Big Chungus", new Banco());
        jugador.pedirAlBanco(new Madera(), 1);
        jugador.pedirAlBanco(new Ladrillo(), 1);
        jugador.pedirAlBanco(new Trigo(), 1);
        jugador.pedirAlBanco(new Oveja(), 1);
        Poblado poblado = new Poblado(jugador);

        poblado.cobrarleAJugador();
        Assertions.assertEquals(0, jugador.cantidadCartas());
    }

    @Test
    public void test10JugadoPagaDesarrollo(){

        Jugador jugador = new Jugador("Big Chungus", new Banco());
        jugador.pedirAlBanco(new Piedra(), 1);
        jugador.pedirAlBanco(new Trigo(), 1);
        jugador.pedirAlBanco(new Oveja(), 1);

        jugador.adquirirDesarrollo();

        Assertions.assertEquals(0, jugador.cantidadCartas());

    }

}
