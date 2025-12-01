package Recurso;

import Jugador.Jugador;

import java.util.HashMap;
import java.util.List;
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

    public int getCantidad(int cantidad) {
        return cantidad + this.cantidad;
    }
}

