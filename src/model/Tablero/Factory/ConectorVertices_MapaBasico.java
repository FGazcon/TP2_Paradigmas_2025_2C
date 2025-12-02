package model.Tablero.Factory;

import model.Tablero.Vertice.Vertice;

public class ConectorVertices_MapaBasico {

    public static void generarGrafo(Vertice[] vertices) {

        //Esta secuencia horrible genera las 72 Aristas entre los 54 Vertices.

        Vertice.agregarArista(vertices[0],vertices[1], new int[]{0,1});
        Vertice.agregarArista(vertices[0],vertices[29], new int[]{0,29});
        Vertice.agregarArista(vertices[1],vertices[2], new int[]{1,2});
        Vertice.agregarArista(vertices[2],vertices[3], new int[]{2,3});
        Vertice.agregarArista(vertices[2],vertices[31],new int[]{2,31});
        Vertice.agregarArista(vertices[3],vertices[4], new int[]{3,4});
        Vertice.agregarArista(vertices[4],vertices[5], new int[]{4,5});
        Vertice.agregarArista(vertices[4],vertices[33], new int[]{4,33});
        Vertice.agregarArista(vertices[5],vertices[6], new int[]{5,6});
        Vertice.agregarArista(vertices[6],vertices[7], new int[]{6,7});
        Vertice.agregarArista(vertices[7],vertices[8], new int[]{7,8});
        Vertice.agregarArista(vertices[7],vertices[34], new int[]{7,34});
        Vertice.agregarArista(vertices[8],vertices[9], new int[]{8,9});
        Vertice.agregarArista(vertices[9],vertices[10], new int[]{9,10});
        Vertice.agregarArista(vertices[9],vertices[16], new int[]{9,16});
        Vertice.agregarArista(vertices[10],vertices[11], new int[]{10,11});
        Vertice.agregarArista(vertices[11],vertices[12], new int[]{11,12});
        Vertice.agregarArista(vertices[12],vertices[13], new int[]{12,13});
        Vertice.agregarArista(vertices[12],vertices[37], new int[]{12,37});
        Vertice.agregarArista(vertices[13],vertices[14], new int[]{13,14});
        Vertice.agregarArista(vertices[14],vertices[15], new int[]{14,15});
        Vertice.agregarArista(vertices[14],vertices[39], new int[]{14,39});
        Vertice.agregarArista(vertices[15],vertices[16], new int[]{15,16});
        Vertice.agregarArista(vertices[16],vertices[17], new int[]{16,17});
        Vertice.agregarArista(vertices[17],vertices[18], new int[]{17,18});
        Vertice.agregarArista(vertices[17],vertices[40], new int[]{17,40});
        Vertice.agregarArista(vertices[18],vertices[19], new int[]{18,19});
        Vertice.agregarArista(vertices[19],vertices[20], new int[]{19,20});
        Vertice.agregarArista(vertices[19],vertices[42], new int[]{19,42});
        Vertice.agregarArista(vertices[20],vertices[21], new int[]{20,21});
        Vertice.agregarArista(vertices[21],vertices[22], new int[]{21,22});
        Vertice.agregarArista(vertices[22],vertices[44], new int[]{22,44});
        Vertice.agregarArista(vertices[22],vertices[23], new int[]{22,23});
        Vertice.agregarArista(vertices[23],vertices[24], new int[]{23,24});
        Vertice.agregarArista(vertices[24],vertices[25], new int[]{24,25});
        Vertice.agregarArista(vertices[24],vertices[45], new int[]{24,45});
        Vertice.agregarArista(vertices[25],vertices[26], new int[]{25,26});
        Vertice.agregarArista(vertices[26],vertices[27], new int[]{26,27});
        Vertice.agregarArista(vertices[27],vertices[28], new int[]{27,28});
        Vertice.agregarArista(vertices[27],vertices[46], new int[]{27,46});
        Vertice.agregarArista(vertices[28],vertices[29], new int[]{28,29});
        Vertice.agregarArista(vertices[29],vertices[30], new int[]{29,30});
        Vertice.agregarArista(vertices[30],vertices[31], new int[]{30,31});
        Vertice.agregarArista(vertices[30],vertices[47], new int[]{30,47});
        Vertice.agregarArista(vertices[31],vertices[32], new int[]{31,32});
        Vertice.agregarArista(vertices[32],vertices[33], new int[]{32,33});
        Vertice.agregarArista(vertices[32],vertices[48], new int[]{32,48});
        Vertice.agregarArista(vertices[33],vertices[34], new int[]{33,34});
        Vertice.agregarArista(vertices[34],vertices[35], new int[]{34,35});
        Vertice.agregarArista(vertices[35],vertices[36], new int[]{35,36});
        Vertice.agregarArista(vertices[35],vertices[49], new int[]{35,49});
        Vertice.agregarArista(vertices[36],vertices[37], new int[]{36,37});
        Vertice.agregarArista(vertices[37],vertices[38], new int[]{37,38});
        Vertice.agregarArista(vertices[38],vertices[39], new int[]{38,39});
        Vertice.agregarArista(vertices[38],vertices[50], new int[]{38,50});
        Vertice.agregarArista(vertices[39],vertices[40], new int[]{39,40});
        Vertice.agregarArista(vertices[40],vertices[41], new int[]{40,41});
        Vertice.agregarArista(vertices[41],vertices[42], new int[]{41,42});
        Vertice.agregarArista(vertices[41],vertices[51], new int[]{41,51});
        Vertice.agregarArista(vertices[42],vertices[43], new int[]{42,43});
        Vertice.agregarArista(vertices[43],vertices[44], new int[]{43,44});
        Vertice.agregarArista(vertices[44],vertices[45], new int[]{44,45});
        Vertice.agregarArista(vertices[44],vertices[52], new int[]{44,52});
        Vertice.agregarArista(vertices[45],vertices[46], new int[]{45,46});
        Vertice.agregarArista(vertices[46],vertices[47], new int[]{46,47});
        Vertice.agregarArista(vertices[47],vertices[53], new int[]{47,53});
        Vertice.agregarArista(vertices[48],vertices[49], new int[]{48,49});
        Vertice.agregarArista(vertices[48],vertices[53], new int[]{48,53});
        Vertice.agregarArista(vertices[49],vertices[50], new int[]{49,50});
        Vertice.agregarArista(vertices[50],vertices[51], new int[]{50,51});
        Vertice.agregarArista(vertices[51],vertices[52], new int[]{51,52});
        Vertice.agregarArista(vertices[52],vertices[53], new int[]{52,53});

    }

}