package Terreno;

import Produccion.Recurso;
import Tablero.Vertice.Vertice;

public class Desierto extends Terreno {

    public Recurso darRecurso() {
        System.out.println("El Desierto no produce recursos.");
        return null;
    }

}
