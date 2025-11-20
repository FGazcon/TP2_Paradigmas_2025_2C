package Terreno;

import Errores.DesiertoNoProduceNada;
import Recurso.Recurso;

public class Desierto extends Terreno{

    public Recurso darRecurso(){
        throw new DesiertoNoProduceNada();
    }

}
