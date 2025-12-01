package Jugador;

import Banco.Banco;
import Comercio.ReglaDeComercio;
import Recurso.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jugador {

    private String nombre;
    private int puntaje;
    private ReglaDeComercio reglaDeComercio;
    private Map<String, Recurso> mapaRecursos;
    private Madera madera;
    private Ladrillo ladrillo;
    private Oveja oveja;
    private Trigo trigo;
    private Piedra piedra;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.mapaRecursos = Recurso.crearMazoProduccionJugador();
    }

    public String elegirCartaRandomADescartar(){
        List<Recurso> disponibles = this.mapaRecursos.values().stream().filter(r -> r.tieneAlMenos(1)).toList();

        if (disponibles.isEmpty()) {
            return null;
        }

        Recurso elegido = disponibles.get(
                (int) (Math.random() * disponibles.size())
        );

        elegido.descartar(1);

        return elegido.nombre();
    }

    public void descartarMitad(){
        Banco banco = new Banco();
        if(this.cantidadCartas() > 7) {
            for (int i = 1; i <= this.cantidadCartas(); i++) {
                banco.sumarRecurso(elegirCartaRandomADescartar(), 1);
            }
        }
    }

    public void dejarseRobarPorJugador(Jugador jugador) {
        jugador.sumarRecurso(elegirCartaRandomADescartar(), 1);
    }

    public int cantidadCartas(){
        int cantidad = 0;
        for (Map.Entry<String, Recurso> recurso : mapaRecursos.entrySet()) {
            cantidad = recurso.getValue().getCantidad(cantidad);
        }
        return cantidad;
    }

    public void sumarPunto(){
        this.puntaje++;
    }

    public int calcularPuntaje() {
        int puntaje = this.puntaje;
        //Sumar PV
        //Sumar Cartas especiales
        return puntaje;
    }

    public void comerciarConOtroJugador(Jugador jugador, Map<String, Integer> recursosOfrecidos, Map<String, Integer> recursosDeseados) {
        if(puedeEntregar(recursosOfrecidos)){
            jugador.recibirOfertaComercio(this, recursosOfrecidos, recursosDeseados);
        }
    }

    public void recibirOfertaComercio(Jugador jugador, Map<String, Integer> recursosOfrecidos, Map<String, Integer> recursosDeseados){
        if(puedeEntregar(recursosDeseados)){
            jugador.sumarVariosRecursos(recursosDeseados);
            sumarVariosRecursos(recursosOfrecidos);
        }
    }

    private void sumarVariosRecursos(Map<String, Integer> recursosOfrecidos){
        for (Map.Entry<String, Integer> recurso : recursosOfrecidos.entrySet()) {
            mapaRecursos.get(recurso.getKey()).sumar(recurso.getValue());
        }
    }

    public boolean puedeEntregar(Map<String, Integer> recursosAEntregar) {
        for (Map.Entry<String, Integer> entry : recursosAEntregar.entrySet()) {
            if(!mapaRecursos.get(entry.getKey()).tieneAlMenos(entry.getValue())){
                return false;
            }
        }
        return true;
    }

    public void sumarRecurso(Piedra piedra, int cantidad){
        this.piedra.sumar(cantidad);
    }

    public void sumarRecurso(Ladrillo ladrillo, int cantidad){
        this.ladrillo.sumar(cantidad);
    }

    public void sumarRecurso(Oveja oveja, int cantidad){
        this.oveja.sumar(cantidad);
    }

    public void sumarRecurso(Madera madera, int cantidad){
        this.madera.sumar(cantidad);
    }

    public void sumarRecurso(Trigo trigo, int cantidad){
        this.trigo.sumar(cantidad);
    }

    public void descartarRecurso(Trigo trigo, int cantidad){
        this.mapaRecursos.get("Trigo").descartar(cantidad);
    }

    public void descartarRecurso(, int cantidad){
        this.mapaRecursos.get().descartar(cantidad);
    }

    public void descartarRecurso(, int cantidad){
        this.mapaRecursos.get().descartar(cantidad);
    }

    public void descartarRecurso(, int cantidad){
        this.mapaRecursos.get().descartar(cantidad);
    }

    public void descartarRecurso(, int cantidad){
        this.mapaRecursos.get().descartar(cantidad);
    }

    public boolean tieneAlMenos(String recurso, int cantidad){
        return this.mapaRecursos.get(recurso).tieneAlMenos(cantidad);
    }

    public void pedirAlBanco(String recurso, int cantidad){
        Banco banco =  Banco.getBanco();
        banco.jugadorLeSolicitaRecurso(this, recurso, cantidad);
    }

}