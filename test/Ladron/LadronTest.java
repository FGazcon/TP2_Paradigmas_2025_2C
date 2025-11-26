package Ladron;

import Errores.HexagonoBajoAsalto;
import Jugador.Jugador;
import Recurso.Madera;
import Tablero.Factory.Factory_MapaBasico;
import Tablero.Hexagono;
import Terreno.Terreno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

public class LadronTest {

    @Test
    public void test01LadronSeMueve(){
        Terreno terreno = mock(Terreno.class);
        List<Hexagono> hexagonos = Factory_MapaBasico.crearHexagonosBasico();
        Ladron ladron = new Ladron(hexagonos.get(1));
        Jugador jugador1 = mock(Jugador.class);

        ladron.moverLadron(hexagonos.get(0), jugador1);

        Assertions.assertThrows(HexagonoBajoAsalto.class, ()->{hexagonos.get(0).activarHexagono();});

    }

    @Test
    public void test02MoverLadronVariasVeces() {
        Terreno terreno = mock(Terreno.class);
        List<Hexagono> hexagonos = Factory_MapaBasico.crearHexagonosBasico();
        Ladron ladron = new Ladron(hexagonos.get(1));
        Jugador jugador1 = mock(Jugador.class);

        ladron.moverLadron(hexagonos.get(4), jugador1);
        ladron.moverLadron(hexagonos.get(10), jugador1);
        ladron.moverLadron(hexagonos.get(12), jugador1);
        ladron.moverLadron(hexagonos.get(7), jugador1);

        Assertions.assertThrows(HexagonoBajoAsalto.class, ()->{hexagonos.get(7).activarHexagono();});
    }

    @Test
    public void test03HexagonoRobado(){
        List<Terreno> terrenos = Factory_MapaBasico.pedirTerrenos();
        List<Integer> numerosMezclados = Factory_MapaBasico.generarNumerosMezclados();
        List<Hexagono> hexagonos = Factory_MapaBasico.generarHexagonosNoFijos(numerosMezclados,terrenos);
        Ladron ladron = new Ladron(hexagonos.get(0));
        Jugador jugador1 = new Jugador("Neymar");
        Jugador jugador2 = new Jugador("Pele");
        jugador1.pedirAlBanco(new Madera());
        jugador1.pedirAlBanco(new Madera());





    }




}