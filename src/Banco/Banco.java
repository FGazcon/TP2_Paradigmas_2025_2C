package Banco;

import Desarrollo.MazoDesarrolloGeneral;
import Negociantes.Negociantes;
import Produccion.Carta;
import Produccion.MazoProduccion;
import Recurso.Recurso;

public class Banco extends Negociantes {

    private MazoProduccion mazoDeProduccion;
    private MazoDesarrolloGeneral mazoDesarrollo;
    private static Banco banco =  new Banco();

    public Banco(){
        this.mazoDesarrollo = MazoDesarrolloGeneral.generarMazoDesarrolloBanco();
        this.mazoDeProduccion = new MazoProduccion();
    }

    public static Banco getBanco() {
        return banco;
    }

    public Carta darRecurso(Recurso recurso){

        return this.mazoDeProduccion.recibirRecurso(recurso);

    }

    public void recibirRecurso(Recurso recurso){
        this.mazoDeProduccion.recibirRecurso(recurso);
    }

}
