package Comercio;

public interface ReglaDeComercio {

    ReglaDeComercio intentarCambiarA(CuatroPorUno reglaDeComercio);

    ReglaDeComercio intentarCambiarA(TresPorUno reglaDeComercio);

    ReglaDeComercio intentarCambiarA(DosPorUno reglaDeComercio);

    ReglaDeComercio intentarCambiarA(ReglaDeComercio reglaDeComercio);
}