package Jugabilidad;

import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.Turno;
import model.Catan.TurnoGeneral;
import model.Jugador.Jugador;
import model.Recurso.*;
import model.Tablero.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JugabilidadTest {


    @Test
    public void test01PartidaCompleta() {

        Banco banco = new Banco();
        Jugador j1 = new Jugador("Felipe", banco);
        Jugador j2 = new Jugador("Joaquin", banco);

        Catan catan = new Catan(List.of(j1, j2), banco);
        Tablero tablero = catan.getTablero();
        int vertice1 = 0;
        int vertice2 = 1;
        int vertice3 = 2;

        int verticeA = 45;
        int verticeB = 46;
        int verticeC = 47;


        Turno turnoInicial = catan.getTurno();

        //Primer turno Felipe
        turnoInicial.construirPoblado(vertice1);
        turnoInicial.construirCarretera(new int[]{vertice1, vertice2});

        catan.terminarTurno();
        turnoInicial = catan.getTurno();

        //Primer turno Joaquin
        turnoInicial.construirPoblado(verticeA);
        turnoInicial.construirCarretera(new int[]{verticeA, verticeB});

        catan.terminarTurno();
        turnoInicial = catan.getTurno();

        //Segundo turno Joaquin
        turnoInicial.construirPoblado(verticeC);
        turnoInicial.construirCarretera(new int[]{verticeC, verticeB});

        catan.terminarTurno();
        turnoInicial = catan.getTurno();

        //Segundo turno Felipe
        turnoInicial.construirPoblado(vertice3);
        turnoInicial.construirCarretera(new int[]{vertice3, vertice2});

        Assertions.assertEquals(2, j1.calcularPuntaje());
        Assertions.assertEquals(2, j2.calcularPuntaje());

        //Termina fase inicial
        catan.terminarTurno();
        TurnoGeneral turnoGeneral = catan.getTurno().getTurnoGeneral();

        //Simulamos la obtencion de recursos
        j1.pedirAlBanco(new Madera(), 5);
        j1.pedirAlBanco(new Ladrillo(), 5);
        j1.pedirAlBanco(new Trigo(), 9);
        j1.pedirAlBanco(new Oveja(), 5);
        j1.pedirAlBanco(new Piedra(), 9);

        j2.pedirAlBanco(new Madera(), 5);
        j2.pedirAlBanco(new Ladrillo(), 5);
        j2.pedirAlBanco(new Trigo(), 5);
        j2.pedirAlBanco(new Oveja(), 5);
        j2.pedirAlBanco(new Piedra(), 5);

        int vertice4 = 3;
        int vertice5 = 4;
        int vertice6 = 5;

        turnoGeneral.construirCarretera(new int[]{vertice3, vertice4});
        turnoGeneral.construirCarretera(new int[]{vertice5, vertice4});
        turnoGeneral.construirPoblado(vertice5);
        turnoGeneral.construirCiudad(vertice5);
        turnoGeneral.construirCiudad(vertice3);
        turnoGeneral.construirCiudad(vertice1);

        Assertions.assertEquals(6, j1.calcularPuntaje());

        turnoGeneral.construirCarretera(new int[]{4, 5});

        Assertions.assertEquals(8, j1.calcularPuntaje());

        for(int i = 0; i < 25; i++){
            j1.pedirAlBanco(new Trigo(), 1);
            j1.pedirAlBanco(new Piedra(), 1);
            j1.pedirAlBanco(new Oveja(), 1);
            turnoGeneral.comprarDesarrollo();
        }

        Assertions.assertEquals(j1, catan.getGanador());
    }

}