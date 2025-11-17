package Jugador;

import Banco.Banco;
import Desarrollo.MazoDesarrolloGeneral;
import Errores.BancoNoTieneRecurso;
import Negociantes.Negociantes;
import Produccion.MazoProduccion;
import Produccion.Recurso;

public class Jugador extends Negociantes{

    private Banco banco;
    private MazoProduccion mazoProduccion;
    private MazoDesarrolloGeneral mazoDesarrolloGeneral;

    public Jugador(Banco banco) {
        this.banco = banco;
        this.mazoProduccion = new MazoProduccion();
        this.mazoDesarrolloGeneral = new MazoDesarrolloGeneral();
    }

    public void pedirAlBanco(Recurso recurso){
        if (this.banco.darRecurso(recurso)){
            this.mazoProduccion.recibirRecurso(recurso);
        } else {
            throw new BancoNoTieneRecurso();
        }
    }

}
