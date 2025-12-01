package Recurso;

import Banco.Banco;
import Comercio.ReglaDeComercio;
import Jugador.Jugador;

import java.util.HashMap;
import java.util.Map;

public abstract class Recurso{

    private int cantidad;
    private ReglaDeComercio reglaDeComercio;

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
    }

    public abstract void transferirAlBanco(Banco banco, int cantidad);
    public abstract void transferirAJugador(Jugador jugador, int cantidad);
    public abstract void descartarAlBanco(Banco banco, int cantidad);
    public abstract void descartarAJugador(Jugador jugador, int cantidad);
    public abstract void hacerQuejugadorSoliciteABanco(Jugador jugador, int cantidad);

    public Recurso(int cantidad){
        this.cantidad = cantidad;
    }

    public abstract String nombre();

    public void descartar(int cantidad){
        this.cantidad = this.cantidad - cantidad;
    }

    public void sumar(int cantidad){
        this.cantidad = this.cantidad + cantidad;
    }

    public boolean tieneAlMenos(int cantidad){
        return this.cantidad >= cantidad;
    }

    public int sumadorCantidad(int cantidad) {
        return cantidad + this.cantidad;
    }

    public abstract boolean jugadorTieneAlMenos(Jugador jugador, int cantidad);

    public abstract void darReglaA(Jugador jugador, ReglaDeComercio reglaDeComercio);

    public void comerciar(int cantidad, Recurso recursoDeseado){
        reglaDeComercio.intentarComerciar(this, cantidad, recursoDeseado);
    }

    public void cambiarRegla(ReglaDeComercio reglaDeComercio){
        this.reglaDeComercio = reglaDeComercio.intentarCambiarA(this.reglaDeComercio);
    }
}

