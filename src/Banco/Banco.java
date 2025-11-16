package Banco;

import Desarrollo.MazoDesarrolloGeneral;
import Negociantes.Negociantes;
import Produccion.MazoProduccion;
import Produccion.Recurso;

public class Banco extends Negociantes {

    private MazoProduccion mazoDeProduccion;
    private MazoDesarrolloGeneral mazoDesarrollo;

    public Banco(){
        this.mazoDesarrollo = new MazoDesarrolloGeneral();
        this.mazoDeProduccion = new MazoProduccion();
    }

    public boolean darRecurso(Recurso recurso){

        return this.mazoDeProduccion.darRecurso(recurso);

    }

}
