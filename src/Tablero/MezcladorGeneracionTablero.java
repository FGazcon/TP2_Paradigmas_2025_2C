package Tablero;

import Tablero.Vertice.Vertice;
import Terreno.Terreno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MezcladorGeneracionTablero {

    public static List<Hexagono> mezclarNumerosYHexagonos(Vertice[][] vertices, List<Terreno> terrenos) {

        List<Integer> numerosMezclados = generarNumerosMezclados();

        List<Hexagono> hexagonos = generarHexagonosNoFijos(numerosMezclados, terrenos);

        Collections.shuffle(hexagonos);

        for (int i = 0; i < hexagonos.size(); i++) {
            hexagonos.get(i).setVertices(vertices[i]);
        }

        return hexagonos;

    }

    private static List<Hexagono> generarHexagonosNoFijos(List<Integer> numerosMezclados, List<Terreno> terrenos) {

        List<Hexagono> hexagonos = new ArrayList<Hexagono>();

        for (int i = 0; i < numerosMezclados.size(); i++) {
            hexagonos.add(new Hexagono(terrenos.removeFirst(), numerosMezclados.removeFirst()));
        }
        hexagonos.add(new Hexagono(terrenos.removeFirst(), 7));

        return  hexagonos;
    }

    private static List<Integer> generarNumerosMezclados(){

        List<Integer> numerosMezclados = new ArrayList<>();

        int[] arrayNumero = {2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12};

        for(int i = 0; i < arrayNumero.length; i++){
            numerosMezclados.add(arrayNumero[i]);
        }

        return numerosMezclados;

    }

}
