package model.Recurso;

import model.Banco.Banco;
import model.Comercio.CuatroPorUno;
import model.Comercio.ReglaDeComercio;
import model.Jugador.Jugador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public void comerciarConBanco(Jugador jugador, int cantidad, Recurso recursoDeseado, Banco banco) {
        System.out.println(reglaDeComercio);
        reglaDeComercio.intentarComerciar(jugador, this, cantidad, recursoDeseado, banco);
    }

    public void cambiarRegla(ReglaDeComercio reglaDeComercio){
        this.reglaDeComercio = reglaDeComercio.intentarCambiarA(this.reglaDeComercio);
    }

    public abstract Recurso getRecursoJugador(Jugador jugad);
    public abstract Recurso getRecursoBanco(Banco banco);


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        return this.getClass() == o.getClass();
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    public Recurso obtenerRecursoDeJugador(Jugador jugador) {
        return this.getRecursoJugador(jugador);
    }

    public Recurso obtenerRecursoDeBanco(Banco banco) {
        return this.getRecursoBanco(banco);
    }

    public void sumarCantidadDeUnRecursoAJugador(Jugador jugador, int cantidad) {
        Recurso recurso = this.obtenerRecursoDeJugador(jugador);
        recurso.sumar(cantidad);
    }

    public void descartarCantidadDeUnRecursoAJugador(Jugador jugador, int cantidad) {
        Recurso recurso = this.obtenerRecursoDeJugador(jugador);
        recurso.descartar(cantidad);
    }

    public boolean verificarSiJugadorTieneAlMenosUnaCantidadDeUnRecurso(Jugador jugador, int cantidad) {
        Recurso recurso = this.obtenerRecursoDeJugador(jugador);
        return recurso.tieneAlMenos(cantidad);
    }

    public void sumarCantidadDeUnRecursoABanco(Banco banco, int cantidad) {
        Recurso recurso = this.obtenerRecursoDeBanco(banco);
        recurso.sumar(cantidad);
    }

    public void descartarCantidadDeUnRecursoABanco(Banco banco, int cantidad) {
        Recurso recurso = this.obtenerRecursoDeBanco(banco);
        recurso.descartar(cantidad);
    }

    public boolean verificarSiBancoTieneAlMenosUnaCantidadDeUnRecurso(Banco banco, int cantidad) {
        Recurso recurso = this.obtenerRecursoDeBanco(banco);
        return recurso.tieneAlMenos(cantidad);
    }

    public void cambiarReglaDeComercioDeUnJugador(Jugador jugador, ReglaDeComercio reglaDeComercio) {
        Recurso recurso = this.obtenerRecursoDeJugador(jugador);
        recurso.cambiarRegla(reglaDeComercio);
    }

    public void comerciarRecursoDeUnJugadorConElBanco(Jugador jugador, int cantidad,  Recurso recursoDeseado, Banco banco) {
        Recurso recurso = this.obtenerRecursoDeJugador(jugador);
        System.out.println(recurso.nombre());
        recurso.comerciarConBanco(jugador, cantidad, recursoDeseado, banco);
    }

        ///

    public static List<Recurso> crearListaDeRecursos(){
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Trigo());
        recursos.add(new Piedra());
        recursos.add(new Madera());
        recursos.add(new Oveja());
        recursos.add(new Ladrillo());

        return recursos;

    }

    public void agregarRecursosALista(List<Recurso> recursosLista) {

        for (Recurso r : recursosLista) {
            if (r.getClass().equals(this.getClass())) {  // mismo tipo
                if (r.getCantidad() < this.getCantidad()) {
                    r.sumar(1);
                }
                //return;
            }
        }
    }
    public void agregarALaListaSinRestriccion(List<Recurso> recursosNecesitados){
        for (Recurso r : recursosNecesitados) {
            if (r.getClass().equals(this.getClass())) {  // mismo tipo
                System.out.println("Recursos elegidos " + r.getCantidad());

                    r.sumar(1);
                System.out.println("Recursos elegidos " + r.getCantidad());

                break;
            }
        }
    }


}

