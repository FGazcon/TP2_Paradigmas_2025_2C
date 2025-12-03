package model.Comercio;

import model.Banco.Banco;
import model.Recurso.Recurso;
import model.Jugador.Jugador;

public interface ReglaDeComercio {

    ReglaDeComercio intentarCambiarA(CuatroPorUno reglaDeComercio);

    ReglaDeComercio intentarCambiarA(TresPorUno reglaDeComercio);

    ReglaDeComercio intentarCambiarA(DosPorUno reglaDeComercio);

    ReglaDeComercio intentarCambiarA(ReglaDeComercio reglaDeComercio);

    void intentarComerciar(Jugador jugador, Recurso recursoOfrecido, int cantidad, Recurso recursoDeseado, Banco banco);
}