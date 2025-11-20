package Banco;

import Desarrollo.MazoDesarrolloGeneral;
import Negociantes.Negociantes;
import Produccion.Carta;
import Produccion.MazoProduccion;
import Recurso.Recurso;

public class Banco extends Negociantes {

    private MazoProduccion mazoDeProduccion;
    private MazoDesarrolloGeneral mazoDesarrollo;

    public Banco(){
        this.mazoDesarrollo = MazoDesarrolloGeneral.generarMazoDesarrolloBanco();
        this.mazoDeProduccion = new MazoProduccion();
    }

    public Carta darRecurso(Recurso recurso){

        return this.mazoDeProduccion.recibirRecurso(recurso);

    }

}
