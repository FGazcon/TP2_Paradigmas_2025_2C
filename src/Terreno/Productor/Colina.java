package Terreno.Productor;

import Recurso.Recurso;
import Recurso.Ladrillo;

public class Colina extends Productor {

    public Recurso darRecurso(){

        return new Ladrillo();

    }

}
