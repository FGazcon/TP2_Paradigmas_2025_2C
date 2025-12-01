package Comercio;

public class CuatroPorUno implements ReglaDeComercio {
    @Override
    public ReglaDeComercio intentarCambiarA(TresPorUno reglaNueva) {
        return reglaNueva;
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

}