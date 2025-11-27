package Banco;

import Desarrollo.MazoDesarrolloGeneral;
import Jugador.Jugador;
import Produccion.MazoProduccion;
import Recurso.Recurso;

public class Banco {

    private MazoProduccion mazoDeProduccion;
    private MazoDesarrolloGeneral mazoDesarrollo;
    private static Banco banco =  new Banco();

    public Banco(){
        this.mazoDesarrollo = MazoDesarrolloGeneral.generarMazoDesarrolloBanco();
        this.mazoDeProduccion = MazoProduccion.crearMazoParaBanco();
    }

    public static Banco getBanco() {
        return banco;
    }

    public boolean darRecurso(Recurso recurso){
        return this.mazoDeProduccion.pedirRecurso(recurso);
    }

    public void recibirRecurso(Recurso recurso){
        this.mazoDeProduccion.añadirRecurso(recurso);
    }

    public boolean intercambiarConJugador(Jugador jugador, Recurso recursoPedido, Recurso recursoOfrecido) {

        int recursosNecesarios = 4;

        if (!jugador.puedeEntregarNDelMismoTipo(recursoOfrecido, recursosNecesarios)) {
            return false;
        }

        if (!this.darRecurso(recursoPedido)) {
            return false;
        }

        jugador.entregarNRecursosAlBanco(recursoOfrecido, recursosNecesarios);
        jugador.recibirRecursoDesdeBanco(recursoPedido);

        return true;
    }


}
