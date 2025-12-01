package Jugador;

import Recurso.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
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

        jugador.descartarMitad();
        int cantidadCartasRecibidas = jugador.cantidadCartas();
        int cantidadCartasJugadorEsperado = 5;

        Assertions.assertEquals(cantidadCartasJugadorEsperado, cantidadCartasRecibidas);

    }

    @Test
    public void test03JugadorAumentaSuPuntaje(){
        Jugador jugador = new Jugador("Sapardo");
        jugador.sumarPunto();
        jugador.sumarPunto();

        Assertions.assertEquals(jugador.calcularPuntaje(), 2);
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




    /*
    @Test
    public void test07JugadorPuedeComerciar4a1ConBanco() {
        Jugador jugador = new Jugador("Jugador");

        Madera madera = new Madera();
        Piedra piedra = new Piedra();

        jugador.pedirAlBanco(madera, 4);

        boolean tradeo = jugador.comerciarConBanco(new Madera(), piedra);

        Assertions.assertTrue(tradeo);
        Assertions.assertTrue(jugador.tiene(piedra));
    }

    @Test
    public void test08JugadorNoPuedeComerciarSiNoTiene4Iguales() {
        Jugador jugador = new Jugador("Jugador");

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        Madera madera3 = new Madera();

        jugador.pedirAlBanco(madera1);
        jugador.pedirAlBanco(madera2);
        jugador.pedirAlBanco(madera3);

        boolean tradeo = jugador.comerciarConBanco(new Madera(), new Piedra());

        Assertions.assertFalse(tradeo);
        Assertions.assertFalse(jugador.tiene(new Piedra()));
    }

    @Test
    public void test09JugadorEntregaExactamenteCuatroRecursosAlBanco() {
        Jugador jugador = new Jugador("Jugador");

        Madera madera1 = new Madera();
        Madera madera2 = new Madera();
        Madera madera3 = new Madera();
        Madera madera4 = new Madera();
        Madera madera5 = new Madera();

        jugador.pedirAlBanco(madera1);
        jugador.pedirAlBanco(madera2);
        jugador.pedirAlBanco(madera3);
        jugador.pedirAlBanco(madera4);
        jugador.pedirAlBanco(madera5);

        jugador.comerciarConBanco(new Madera(), new Piedra());

        // Va a tener la madera que sobró y la piedra que recibió del tradeo

        Assertions.assertEquals(2, jugador.cantidadCartas());
    }

    */

}
