package Terreno.Productor;

import Produccion.Ladrillo;
import Produccion.Recurso;
import Tablero.Vertice.Vertice;
import Terreno.Terreno;

public class Colina extends Productor {

    public Recurso darRecurso() {
        return new Ladrillo();
    }

}
