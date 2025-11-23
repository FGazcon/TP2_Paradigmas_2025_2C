package Ladron;

import Jugador.Jugador;
import Recurso.RecursoFactory;
import Tablero.Factory.Factory_MapaBasico;
import Tablero.Hexagono;
import Terreno.Terreno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LadronTest {

    @Test
    public void test01LadronComienzaEnCero() {
        Ladron ladron = new Ladron();
        Assertions.assertEquals(0, ladron.moverLadron(0));
    }

    @Test
    public void test02SingletonDevuelveSiempreLaMismaInstancia() {
        Ladron l1 = Ladron.getLadron();
        Ladron l2 = Ladron.getLadron();

        Assertions.assertSame(l1, l2);
    }

    @Test
    public void test03LadronSeMueve(){

        Ladron ladron = Ladron.getLadron();

        ladron.moverLadron(14);

        int ubicacionLadron = ladron.moverLadron(2);

        Assertions.assertEquals(ubicacionLadron,14);

    }

    @Test
    public void test04MoverLadronVariasVeces() {
        Ladron ladron = Ladron.getLadron();
        ladron.ubicarseEn(0);

        Assertions.assertEquals(0, ladron.moverLadron(4));  // de 0 → 4
        Assertions.assertEquals(4, ladron.moverLadron(7));  // de 4 → 7
        Assertions.assertEquals(7, ladron.moverLadron(2));  // de 7 → 2
    }

    @Test
    public void test05HexagonoRobado(){
        List<Terreno> terrenos = Factory_MapaBasico.pedirTerrenos();
        List<Integer> numerosMezclados = Factory_MapaBasico.generarNumerosMezclados();
        List<Hexagono> hexagonos = Factory_MapaBasico.generarHexagonosNoFijos(numerosMezclados,terrenos);
        Ladron ladron = Ladron.getLadron();
        Jugador jugador1 = new Jugador("Neymar");
        Jugador jugador2 = new Jugador("Pele");
        jugador1.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));
        jugador1.pedirAlBanco(RecursoFactory.crearRecurso("MaderA"));





    }




}