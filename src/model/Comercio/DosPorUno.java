package model.Comercio;

import model.Banco.Banco;
import model.Jugador.Jugador;
import model.Recurso.Recurso;

public class DosPorUno implements ReglaDeComercio {
    @Override
    public ReglaDeComercio intentarCambiarA(TresPorUno reglaNueva) {
        return this;
    }
    @Override
    public ReglaDeComercio intentarCambiarA(DosPorUno reglaNueva) {
        return this;
    }
    @Override
    public ReglaDeComercio intentarCambiarA(CuatroPorUno reglaNueva) {
        return this;
    }
    @Override
    public ReglaDeComercio intentarCambiarA(ReglaDeComercio reglaDeComercio) {
         return reglaDeComercio.intentarCambiarA(this);
    }
    public void intentarComerciar(Jugador jugador, Recurso recursoOfrecido, int cantidad, Recurso recursoDeseado){
        if (cantidad == 2){
            Banco banco = Banco.getBanco();
            banco.jugadorQuiereIntercambiar(jugador, recursoOfrecido, cantidad, recursoDeseado);
        }
    }

}