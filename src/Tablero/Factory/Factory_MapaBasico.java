package Tablero.Factory;

import Tablero.Hexagono;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factory_MapaBasico {

    public static List<Hexagono> mezclarNumerosYHexagonos(List<Integer> numerosMezclados,List<Terreno>  terrenos) {

        //List<Integer> numerosMezclados = generarNumerosMezclados();

        List<Hexagono> hexagonos = generarHexagonosNoFijos(numerosMezclados,terrenos);

        Collections.shuffle(hexagonos);

        Vertice[][] verticesAsignados = vertices_por_hexagono();

        for (int i = 0; i < hexagonos.size(); i++) {
            hexagonos.get(i).setVertices(verticesAsignados[i]); //Los fija en el mapa via vertices y orden.
        }

        return hexagonos;

    }

    public static List<Hexagono> generarHexagonosNoFijos(List<Integer> numerosMezclados,List<Terreno>  terrenos) {

        List<Hexagono> hexagonos = new ArrayList<Hexagono>();

        //List<Integer> numerosMezclados = generarNumerosMezclados();

        //List<Terreno>  terrenos = pedirTerrenos();

        int size = numerosMezclados.size();
        for (int i = 0; i < size; i++) {
            hexagonos.add(new Hexagono(terrenos.removeFirst(), numerosMezclados.removeFirst()));
            System.out.println(i);
        }
        hexagonos.add(new Hexagono(terrenos.removeFirst(), 7)); //Es el desierto

        return  hexagonos;
    }

    public static List<Integer> generarNumerosMezclados(){

        List<Integer> numerosMezclados = new ArrayList<>();

        int[] arrayNumero = {2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12};

        for(int i = 0; i < arrayNumero.length; i++){
            numerosMezclados.add(arrayNumero[i]);
        }

        Collections.shuffle(numerosMezclados);

        System.out.println("Mezclados " + numerosMezclados.size());

        return numerosMezclados;

    }

    public static Vertice[][] vertices_por_hexagono() {

        Vertice[] vertices = pedirVertices();

        Vertice[] vertices0 = {vertices[0], vertices[1],  vertices[2], vertices[31], vertices[30], vertices[29]};
        Vertice[] vertices1 = {vertices[2], vertices[3], vertices[4], vertices[33], vertices[32], vertices[31]};

        Vertice[] vertices2 = {vertices[4], vertices[5],  vertices[6], vertices[7], vertices[34], vertices[33]};
        Vertice[] vertices3 = {vertices[28], vertices[29], vertices[30], vertices[47], vertices[46], vertices[27]};

        Vertice[] vertices4 = {vertices[30], vertices[31],  vertices[32], vertices[48], vertices[53], vertices[47]};
        Vertice[] vertices5 = {vertices[32], vertices[33], vertices[34], vertices[35], vertices[49], vertices[48]};

        Vertice[] vertices6 = {vertices[34], vertices[7],  vertices[8], vertices[9], vertices[36], vertices[35]};
        Vertice[] vertices7 = {vertices[26], vertices[27], vertices[46], vertices[45], vertices[24], vertices[25]};

        Vertice[] vertices8 = {vertices[46], vertices[47],  vertices[53], vertices[52], vertices[44], vertices[45]};
        Vertice[] vertices9 = {vertices[53], vertices[48], vertices[49], vertices[50], vertices[51], vertices[52]};

        Vertice[] vertices10 = {vertices[49], vertices[35],  vertices[36], vertices[37], vertices[38], vertices[50]};
        Vertice[] vertices11 = {vertices[36], vertices[9], vertices[10], vertices[11], vertices[12], vertices[37]};

        Vertice[] vertices12 = {vertices[24], vertices[45],  vertices[44], vertices[43], vertices[22], vertices[23]};
        Vertice[] vertices13 = {vertices[44], vertices[52], vertices[51], vertices[41], vertices[42], vertices[43]};

        Vertice[] vertices14 = {vertices[51], vertices[50],  vertices[38], vertices[39], vertices[40], vertices[41]};
        Vertice[] vertices15 = {vertices[38], vertices[37], vertices[12], vertices[13], vertices[14], vertices[39]};

        Vertice[] vertices16 = {vertices[22], vertices[43],  vertices[42], vertices[19], vertices[20], vertices[21]};
        Vertice[] vertices17 = {vertices[42], vertices[41], vertices[40], vertices[17], vertices[18], vertices[19]};

        Vertice[] vertices18 = {vertices[40], vertices[39], vertices[14], vertices[15], vertices[16], vertices[17]};

        Vertice[][] vertices_por_hexagono = {vertices0, vertices1, vertices2, vertices3, vertices4, vertices5, vertices6, vertices7, vertices8, vertices9, vertices10, vertices11, vertices12, vertices13, vertices14, vertices15, vertices16, vertices17, vertices18};

        return vertices_por_hexagono;
    }

    public static List<Terreno> pedirTerrenos() {
        return Terreno.generar19Terrenos();
    }

    public static Vertice[] pedirVertices() {
        return Vertice.generarVertices();
    }

}