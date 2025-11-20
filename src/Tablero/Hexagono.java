package Tablero;

import Tablero.Vertice.Estructura.Estructura;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

import java.util.List;

public class Hexagono {

    private Vertice[] vertices;
    private Terreno terreno;
    private int numero;

    public Hexagono(Terreno terreno, int numero) {
        this.vertices = new Vertice[6];
        this.terreno = terreno;
        this.numero = numero;
    }

    public boolean contieneVertice(int numeroDeVertice){
        for(int i = 0; i < vertices.length; i++){
            if (vertices[i].numeroDeVerticeEs(numeroDeVertice)){
                return true;
            }
        }
        return false;
    }

    public void ubicarEstructura(Estructura estructura, int numeroDeVertice){
        for(int i = 0; i < vertices.length; i++){
            if (vertices[i].numeroDeVerticeEs(numeroDeVertice)){
                //vertices[i].ubicarEstructura();
            }
        }
    }

    public void activarseParaVerticeEspecifico(int[] vertices_segundo_poblado) {
        for (int i = 0; i < vertices.length; i++){
            for (int j = 0; j < vertices_segundo_poblado.length; j++){
                if (vertices[i].numeroDeVerticeEs(vertices_segundo_poblado[j])){
                    this.terreno.darRecurso();
                }
            }
        }
    }

    //Consultar si esto esta bien.
    public void setVertices(Vertice[] vertices) {
        this.vertices = vertices;
    }

    public static List<Hexagono> generar19Hexagonos(List<Terreno> terrenosEnHexagonos) {

       // List<Terreno> terrenos = Terreno.generar19Terrenos();
        List<Terreno> terrenos = terrenosEnHexagonos;


        List<Hexagono> hexagonos = MezcladorTablero.mezclarNumerosYHexagonos(vertices_por_hexagono(Vertice.generarVertices()), terrenos);

        for (int i = 0; i < hexagonos.size(); i++) {
            System.out.println(hexagonos.get(i) + " " + i + " " + hexagonos.get(i).numero);
            for (Vertice vertice : hexagonos.get(i).vertices) {
                System.out.println(vertice);
            }
        }
        return hexagonos;

    }

    private static Vertice[][] vertices_por_hexagono(Vertice[] vertices) {

        Vertice[] vertices0 = {vertices[0], vertices[1],  vertices[2], vertices[31], vertices[30], vertices[29]};
        Vertice[] vertices1 = {vertices[2], vertices[3], vertices[4], vertices[33], vertices[32], vertices[31]};

        Vertice[] vertices2 = {vertices[4], vertices[5],  vertices[6], vertices[7], vertices[34], vertices[33]};
        Vertice[] vertices3 = {vertices[28], vertices[29], vertices[30], vertices[47], vertices[46], vertices[27]};

        Vertice[] vertices4 = {vertices[30], vertices[31],  vertices[32], vertices[38], vertices[53], vertices[47]};
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

    private Vertice encontrarVertice(int numeroDeVertice){
        for(int i = 0; i < vertices.length; i++){
            if (vertices[i].numeroDeVerticeEs(numeroDeVertice)){
                return vertices[i];
            }
        }
        return null;
    }
    ///


    public void darRecursoAlVertice(int numeroDeVertice) {
        Vertice vertice;
        vertice = this.encontrarVertice(numeroDeVertice);
        //no se si se peude chequear que vertice sea distinto de null o hay que tirar exception
        if(vertice != null) {
            this.terreno.darRecurso();
        }
    }

    public boolean hexagonoCorrecto(int numeroHexagono){
        return this.numero == numeroHexagono;
    }
/*
    public List<Recurso> otorgarRecursosVertices(){
        //por cada vertice buscar una estructura y otorgarle los recursos
        for (Vertice vertice : this.vertices) {
            terreno.darRecurso();
        }

    }*/

}
