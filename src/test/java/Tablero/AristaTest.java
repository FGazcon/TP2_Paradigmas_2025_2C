package Tablero;

import model.Errores.AristaEstaOcupada;
import model.Errores.AristaFueraDeAlcance;
import model.Jugador.Jugador;
import model.Tablero.Arista.Carretera;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Poblado;
import model.Tablero.Vertice.Estructura.PobladoInicial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class AristaTest {

    @Test
    public void test01UbicarUnaCarreteraSinAdyacenciaEsImposible(){

        Tablero tablero = Tablero.crearTableroBasico();
        Jugador jugador = mock(Jugador.class);
        Carretera carretera = new Carretera(jugador);

        Assertions.assertThrows(AristaFueraDeAlcance.class, ()->{tablero.ubicarCarretera(carretera, new int[]{0, 1});});
    }

    @Test
    public void test02UbicarUnaCarreteraEsPosibleConUnPuebloCercano(){

        Tablero tablero = Tablero.crearTableroBasico();
        Jugador jugador = mock(Jugador.class);
        Carretera carretera = new Carretera(jugador);
        Poblado poblado = new PobladoInicial(jugador);
        tablero.ubicarEstructura(poblado, 0);

        tablero.ubicarCarretera(carretera, new int[]{0,1});

        Assertions.assertThrows(AristaEstaOcupada.class, ()->{tablero.ubicarCarretera(carretera, new int[]{0, 1});});

    }

    @Test
    public void test03UbicarUnaCarreteraEsPosibleConUnaCarreteraCercana(){

        Tablero tablero = Tablero.crearTableroBasico();
        Jugador jugador = mock(Jugador.class);
        Carretera carretera = new Carretera(jugador);
        Carretera carretera2 = new Carretera(jugador);
        Poblado poblado = new PobladoInicial(jugador);
        tablero.ubicarEstructura(poblado, 0);
        tablero.ubicarCarretera(carretera, new int[]{0,1});

        tablero.ubicarCarretera(carretera2, new int[]{1,2});

        Assertions.assertThrows(AristaEstaOcupada.class, ()->{tablero.ubicarCarretera(carretera, new int[]{1, 2});});

    }

    @Test
    public void test04SePuedenUbicarCarreterasConCoordenadasInvertidas(){

        Tablero tablero = Tablero.crearTableroBasico();
        Jugador jugador = mock(Jugador.class);
        Carretera carretera = new Carretera(jugador);
        Poblado poblado = new PobladoInicial(jugador);
        tablero.ubicarEstructura(poblado, 0);

        tablero.ubicarCarretera(carretera, new int[]{0,1});

        Assertions.assertThrows(AristaEstaOcupada.class, ()->{tablero.ubicarCarretera(carretera, new int[]{1, 0});});

    }



}
