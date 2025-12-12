package model.Terreno.Productor;

import model.Recurso.Recurso;
import model.Recurso.Ladrillo;

public class Colina extends Productor {

    public Recurso darRecurso(){

        return new Ladrillo();

    }

}
