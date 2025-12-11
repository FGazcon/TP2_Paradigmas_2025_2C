package model.Terreno;

import model.Errores.DesiertoNoProduceNada;
import model.Recurso.Recurso;

public class Desierto extends Terreno{

    public Recurso darRecurso(){
        throw new DesiertoNoProduceNada();
    }

}
