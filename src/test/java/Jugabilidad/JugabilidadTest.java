package Jugabilidad;

import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.Turno;
import model.Catan.TurnoGeneral;
import model.Catan.TurnoInicial;
import model.Jugador.Jugador;
import model.Tablero.Tablero;
import model.Recurso.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JugabilidadTest {

    @Test
    public void testPartidaCompleta() {

        // ========================
        // 1) Preparar juego
        // ========================
        Banco banco = new Banco();
        Jugador j1 = new Jugador("Felipe", banco);
        Jugador j2 = new Jugador("Joaquin", banco);

        Catan catan = new Catan(List.of(j1, j2), banco);
        Tablero tablero = catan.getTablero();

        // ========================
        // 2) Turno inicial – Jugador 1
        // ========================
        Turno turno = catan.getTurno();
        Assertions.assertTrue(turno instanceof TurnoInicial);

        TurnoInicial t1 = turno.getTurnoInicial();

        // Jugador 1 pone poblado y carretera
        int vA = tablero.getVerticesUnicos().get(0).getNumeroDeVertice();
        int vB = tablero.getVerticesUnicos().get(1).getNumeroDeVertice();

        t1.construirPoblado(vA);
        t1.construirCarretera(new int[]{vA, vB});

        // ========================
        // 3) Termina turno → Jugador 2
        // ========================
        catan.terminarTurno();
        turno = catan.getTurno();
        Assertions.assertTrue(turno instanceof TurnoInicial);

        TurnoInicial t2 = turno.getTurnoInicial();

        int vC = tablero.getVerticesUnicos().get(10).getNumeroDeVertice();
        int vD = tablero.getVerticesUnicos().get(11).getNumeroDeVertice();

        t2.construirPoblado(vC);
        t2.construirCarretera(new int[]{vC, vD});

        // ========================
        // 4) Ahora comienza Turno General
        // ========================
        catan.terminarTurno();
        turno = catan.getTurno();
        Assertions.assertTrue(turno instanceof TurnoGeneral);

        TurnoGeneral tg1 = turno.getTurnoGeneral();

        // ========================
        // 5) Producir recursos
        // ========================
        tg1.tirarDados(5); // cualquier número que tenga hexágonos asociados

        // Ver que al menos se produjo algo
        int total1 = j1.cantidadCartas();
        int total2 = j2.cantidadCartas();
        Assertions.assertTrue(total1 + total2 >= 1);

        // ========================
        // 6) Simular construcciones
        // ========================
        // Le damos recursos manualmente
        j1.sumarRecurso(new Madera(), 5);
        j1.sumarRecurso(new Ladrillo(), 5);
        j1.sumarRecurso(new Trigo(), 5);
        j1.sumarRecurso(new Oveja(), 5);
        j1.sumarRecurso(new Piedra(), 5);

        // Construye un nuevo poblado
        tg1.construirPoblado(vB);

        // Mejora a ciudad (superposición en vertice vB)
        tg1.construirCiudad(vB);

        Assertions.assertTrue(j1.calcularPuntaje() >= 2);

        // ========================
        // 7) Comprar una carta de desarrollo
        // ========================
        tg1.comprarDesarrollo();
        Assertions.assertFalse(j1.getCartasDesarrolloRecienCompradas().isEmpty());

        // ========================
        // 8) Subir artificialmente a 10 puntos y verificar ganador
        // ========================
        for (int i = 0; i < 8; i++) j1.sumarPunto();

        Assertions.assertEquals(10, j1.calcularPuntaje());

        catan.declararGanador(j1);
        Assertions.assertEquals(j1, catan.getGanador());
    }
}