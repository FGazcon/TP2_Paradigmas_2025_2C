package Banco;

import Desarrollo.MazoDesarrolloGeneral;
import Negociantes.Negociantes;
import Produccion.MazoProduccion;
import Produccion.Recurso;

public class Banco extends Negociantes {

    private MazoProduccion mazoDeProduccion;
    private MazoDesarrolloGeneral mazoDesarrollo;
    private static Banco banco = new Banco();

    public Banco(){
        this.mazoDesarrollo = MazoDesarrolloGeneral.generarMazoDesarrolloBanco();
        this.mazoDeProduccion = MazoProduccion.crearMazoParaBanco();
    }

    public static Banco getBanco(){
        return banco;
    }

    public boolean darRecurso(Recurso recurso){

        return this.mazoDeProduccion.darRecurso(recurso);

    }

}
