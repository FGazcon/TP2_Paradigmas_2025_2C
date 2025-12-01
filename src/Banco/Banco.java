package Banco;

import Desarrollo.MazoDesarrolloGeneral;
import Jugador.Jugador;
import Produccion.MazoProduccion;
import Recurso.Recurso;

import java.util.HashMap;
import java.util.Map;

public class Banco {

    private MazoDesarrolloGeneral mazoDesarrollo;
    private Map<String, Recurso> mazoDeProduccion;
    private static Banco banco =  new Banco();

    public Banco(){
        this.mazoDeProduccion = Recurso.crearMazoProduccionBanco();
        this.mazoDesarrollo = MazoDesarrolloGeneral.generarMazoDesarrolloBanco();
    }

    public static Banco getBanco() {
        return banco;
    }

    public void sumarRecurso(String recurso, int cantidad){
        this.mazoDeProduccion.get(recurso).sumar(cantidad);
    }

    public void descartarRecurso(String recurso, int cantidad){
        this.mazoDeProduccion.get(recurso).descartar(cantidad);
    }

    public void tieneAlMenos(String recurso, int cantidad){
        this.mazoDeProduccion.get(recurso).tieneAlMenos(cantidad);
    }

    public void jugadorLeSolicitaRecurso(Jugador jugador, String recurso, int cantidad) {
        if(mazoDeProduccion.get(recurso).tieneAlMenos(cantidad)){
            jugador.sumarRecurso(recurso, cantidad);
            descartarRecurso(recurso, cantidad);
        }
    }
}
