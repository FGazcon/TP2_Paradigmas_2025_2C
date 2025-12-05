package Ladron;

import model.Banco.Banco;
import model.Errores.HexagonoBajoAsalto;
import model.Jugador.Jugador;
import model.Ladron.Ladron;
import model.Recurso.Madera;
import model.Tablero.Factory.Factory_MapaBasico;
import model.Tablero.Hexagono;
import model.Terreno.Terreno;
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
        Banco banco = new Banco();
        Jugador jugador1 = new Jugador("Neymar", banco);
        Jugador jugador2 = new Jugador("Pele", banco);
        jugador1.pedirAlBanco(new Madera(), 1);
        jugador1.pedirAlBanco(new Madera(), 1);





    }




}