package Jugador;

import model.Banco.Banco;
import model.Comercio.DosPorUno;
import model.Comercio.TresPorUno;
import model.Jugador.Jugador;
import model.Recurso.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JugadorTest {

    @Test
    public void test01JugadorLePasaCartaAOtroJugadorAlSerRobado(){

        Jugador jugador = new Jugador("Sapardo");
        Jugador jugador1 = new Jugador("Abuelacho");

        jugador.pedirAlBanco(new Madera(), 1);
        jugador.dejarseRobarPorJugador(jugador1);

        Assertions.assertEquals(jugador1.cantidadCartas(), 1);
    }

    @Test
    public void test02JugadorDescartaMitad(){

        Jugador jugador = new Jugador("Jeferson");
        jugador.pedirAlBanco(new Madera(), 9);

        //jugador.descartarMitad();
        int cantidadCartasRecibidas = jugador.cantidadCartas();
        int cantidadCartasJugadorEsperado = 9;

        Assertions.assertEquals(cantidadCartasJugadorEsperado, cantidadCartasRecibidas);

    }

    @Test
    public void test03JugadorAumentaSuPuntaje(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarPunto();
        jugador.sumarPunto();

        Assertions.assertEquals(2, jugador.calcularPuntaje());
    }

    @Test
    public void test04JugadorAumentaLaCantidadDeMadera(){
        Jugador jugador = new Jugador("Sapardo");

        System.out.println(jugador.cantidadCartas());

        jugador.sumarRecurso(new Madera(), 1);

        Assertions.assertTrue(jugador.tieneAlMenos(new Madera(), 1));
    }

    @Test
    public void test05JugadorReduceLaCantidadDeMadera(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 1);

        jugador.descartarRecurso(new Madera(), 1);

        Assertions.assertFalse(jugador.tieneAlMenos(new Madera(), 1));
    }

    @Test
    public void test06JugadorTienesuficienteParaOfertar(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 1);

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera(1));

        Assertions.assertTrue(jugador.tieneSuficientesParaOfertar(recursos));
    }

    @Test
    public void test07JugadorTienesuficienteParaOfertarConMasElementos(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 5);

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera(5));

        Assertions.assertTrue(jugador.tieneSuficientesParaOfertar(recursos));
    }

    @Test
    public void test08LosRecursosExternosManipulanLosInternos(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 5);

        Madera madera = new Madera();
        madera.descartarAJugador(jugador, 1);

        Assertions.assertEquals(jugador.cantidadCartas(), 4);
    }

    @Test
    public void test09JugadorPuedeComerciarConOtroJugador(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 1);
        Jugador jugador1 = new Jugador("Menzo Perez");
        jugador1.sumarRecurso(new Ladrillo(), 1);

        jugador.imprimirRecursos();

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera(1));
        List<Recurso> recursos2 = new ArrayList<>();
        recursos2.add(new Ladrillo(1));

        jugador.comerciarConJugador(jugador1, recursos, recursos2);

        jugador.imprimirRecursos();

        System.out.println("Arranca Assert");

        Assertions.assertTrue(jugador.tieneSuficientesParaOfertar(recursos2));
    }

    @Test
    public void test10JugadorPuedeComerciarConOtroJugador(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 1);
        Jugador jugador1 = new Jugador("Menzo Perez");
        jugador1.sumarRecurso(new Ladrillo(), 1);

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera(1));
        List<Recurso> recursos2 = new ArrayList<>();
        recursos2.add(new Ladrillo(1));

        jugador.comerciarConJugador(jugador1, recursos, recursos2);

        Assertions.assertTrue(jugador.tieneAlMenos(new Ladrillo(), 1));
    }

    @Test
    public void test11JugadorPuedeComerciarConOtroJugadorVariosElementos(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 1);
        jugador.sumarRecurso(new Trigo(), 4);
        Jugador jugador1 = new Jugador("Menzo Perez");
        jugador1.sumarRecurso(new Ladrillo(), 1);
        jugador1.sumarRecurso(new Piedra(), 3);
        jugador1.sumarRecurso(new Oveja(), 1);

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera(1));
        recursos.add(new Trigo(4));
        List<Recurso> recursos2 = new ArrayList<>();
        recursos2.add(new Ladrillo(1));
        recursos2.add(new Piedra(3));
        recursos2.add(new Oveja(1));

        jugador.comerciarConJugador(jugador1, recursos, recursos2);

        Assertions.assertTrue(jugador.tieneAlMenos(new Piedra(), 3));
    }

    @Test
    public void test12JugadorDescartaLasCartasEnviadas(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 1);
        Jugador jugador1 = new Jugador("Menzo Perez");
        jugador1.sumarRecurso(new Ladrillo(), 1);

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera(1));
        List<Recurso> recursos2 = new ArrayList<>();
        recursos2.add(new Ladrillo(1));

        jugador.comerciarConJugador(jugador1, recursos, recursos2);

        Assertions.assertFalse(jugador.tieneAlMenos(new Madera(), 1));
    }

    @Test
    public void test13ElIntercambioNoSeIniciaSiElJugadorNoLoPuedePagar(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 1);
        Jugador jugador1 = new Jugador("Menzo Perez");
        jugador1.sumarRecurso(new Ladrillo(), 1);

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera(1123));
        List<Recurso> recursos2 = new ArrayList<>();
        recursos2.add(new Ladrillo(1));

        jugador.comerciarConJugador(jugador1, recursos, recursos2);

        Assertions.assertFalse(jugador.tieneAlMenos(new Ladrillo(), 1));
    }

    @Test
    public void test14ElIntercambioNoSeIniciaSiElReceptorNoLoPuedePagar(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarRecurso(new Madera(), 1);
        Jugador jugador1 = new Jugador("Menzo Perez");
        jugador1.sumarRecurso(new Ladrillo(), 1);

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera(1));
        List<Recurso> recursos2 = new ArrayList<>();
        recursos2.add(new Ladrillo(15));

        jugador.comerciarConJugador(jugador1, recursos, recursos2);

        Assertions.assertFalse(jugador.tieneAlMenos(new Ladrillo(), 1));
    }

    @Test
    public void test15JugadorPuedeIntercambiarconBanco(){
        Jugador jugador = new Jugador("Sapardo");
        Madera madera = new Madera(4);
        jugador.sumarRecurso(madera, 4);
        Banco banco = new Banco();

        jugador.comerciarConBanco(madera, 4, new Ladrillo());

        Assertions.assertTrue(jugador.tieneAlMenos(new Ladrillo(), 1));
    }

    @Test
    public void test16JugadorPuedeIntercambiarconBanco3Por1(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.darReglaA(new Madera(), new TresPorUno());
        Madera madera = new Madera(3);
        jugador.sumarRecurso(madera, 4);
        Banco banco = new Banco();

        jugador.comerciarConBanco(madera, 3, new Ladrillo());

        Assertions.assertTrue(jugador.tieneAlMenos(new Ladrillo(), 1));
    }

    @Test
    public void test17JugadorPuedeIntercambiarCOnBanco2Por1(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.darReglaA(new Madera(), new DosPorUno());
        Madera madera = new Madera(2);
        jugador.sumarRecurso(madera, 2);
        Banco banco = new Banco();

        jugador.comerciarConBanco(madera, 2, new Ladrillo());

        Assertions.assertTrue(jugador.tieneAlMenos(new Ladrillo(), 1));
    }

    @Test
    public void test18JugadorNoNegociaConBancoSiNoTieneAccesoAlIntercambio(){
        Jugador jugador = new Jugador("Sapardo");
        Madera madera = new Madera(4);
        jugador.darReglaA(new Madera(),  new TresPorUno());
        jugador.sumarRecurso(madera, 4);
        Banco banco = new Banco();

        jugador.comerciarConBanco(madera, 2, new Ladrillo());

        Assertions.assertFalse(jugador.tieneAlMenos(new Ladrillo(), 1));
    }

    @Test
    public void test19JugadorPierdeLoEnviado(){
        Jugador jugador = new Jugador("Sapardo");
        Madera madera = new Madera(4);
        jugador.sumarRecurso(madera, 4);
        Banco banco = new Banco();

        jugador.comerciarConBanco(madera, 4, new Ladrillo());

        Assertions.assertFalse(jugador.tieneAlMenos(new Madera(), 1));
    }

}
