package model.Comercio;

import model.Banco.Banco;
import model.Recurso.Recurso;
import model.Jugador.Jugador;

public class TresPorUno implements ReglaDeComercio {
    @Override
    public ReglaDeComercio intentarCambiarA(TresPorUno reglaNueva) {
        return this;
    }
    @Override
    public ReglaDeComercio intentarCambiarA(DosPorUno reglaNueva) {
        return reglaNueva;
    }

    @Override
    public ReglaDeComercio intentarCambiarA(ReglaDeComercio reglaActual) {
        return reglaActual.intentarCambiarA(this);
    }
    @Override
    public ReglaDeComercio intentarCambiarA(CuatroPorUno reglaNueva) {
        return this;
    }

    public void intentarComerciar(Jugador jugador, Recurso recursoOfrecido, int cantidad, Recurso recursoDeseado){
        if (cantidad == 3){
            Banco banco = Banco.getBanco();
            banco.jugadorQuiereIntercambiar(jugador, recursoOfrecido, cantidad, recursoDeseado);
        }
    }


}