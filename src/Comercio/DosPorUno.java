package Comercio;

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
}