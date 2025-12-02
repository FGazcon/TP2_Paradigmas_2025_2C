package model.Recurso;

import model.Banco.Banco;
import model.Comercio.CuatroPorUno;
import model.Comercio.ReglaDeComercio;
import model.Jugador.Jugador;

import java.util.HashMap;
import java.util.Map;

public abstract class Recurso{

    protected int cantidad;
    protected ReglaDeComercio reglaDeComercio;

    public static Map<String,Recurso> crearMazoProduccionBanco() {
        Map<String, Recurso> map = new HashMap<>();
        map.put("Madera", new Madera(19));
        map.put("Trigo", new Trigo(19));
        map.put("Oveja", new Oveja(19));
        map.put("Ladrillo", new Ladrillo(19));
        map.put("Piedra", new Piedra(19));
        return map;
    }

    public static Map<String,Recurso> crearMazoProduccionJugador() {
        Map<String, Recurso> map = new HashMap<>();
        map.put("Madera", new Madera());
        map.put("Trigo", new Trigo());
        map.put("Oveja", new Oveja());
        map.put("Ladrillo", new Ladrillo());
        map.put("Piedra", new Piedra());
        return map;
    }

    public Recurso(){
        this.cantidad = 0;
        this.reglaDeComercio = new CuatroPorUno();
    }
    public int getCantidad(){
        return cantidad;
    }
    public abstract void transferirAlBanco(Banco banco, int cantidad);
    public abstract void transferirAJugador(Jugador jugador, int cantidad);
    public abstract void descartarAlBanco(Banco banco, int cantidad);
    public abstract void descartarAJugador(Jugador jugador, int cantidad);
    public abstract void hacerQuejugadorSoliciteABanco(Jugador jugador, int cantidad);

    public Recurso(int cantidad){
        this.cantidad = cantidad;
        this.reglaDeComercio = new CuatroPorUno();
    }

    public abstract String nombre();

    public void descartar(int cantidad){
        this.cantidad -= cantidad;
    }

    public void sumar(int cantidad){
        this.cantidad += cantidad;
    }

    public abstract boolean tieneAlMenos(int cantidad);

    public int sumadorCantidad(int cantidad) {
        return (cantidad + this.cantidad);
    }

    public abstract boolean jugadorTieneAlMenos(Jugador jugador, int cantidad);
    public abstract boolean bancoTieneAlMenos(Banco banco, int cantidad);

    public abstract void darReglaA(Jugador jugador, ReglaDeComercio reglaDeComercio);

    public void comerciarConBanco(Jugador jugador, int cantidad, Recurso recursoDeseado){
        reglaDeComercio.intentarComerciar(jugador, this, cantidad, recursoDeseado);
    }

    public void cambiarRegla(ReglaDeComercio reglaDeComercio){
        this.reglaDeComercio = reglaDeComercio.intentarCambiarA(this.reglaDeComercio);
    }
}

