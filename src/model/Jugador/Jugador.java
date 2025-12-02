package model.Jugador;

import model.Banco.Banco;
import model.Comercio.ReglaDeComercio;
import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Recurso.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Jugador {

    private String nombre;
    private int puntaje;
    private Map<String, Recurso> mapaRecursos;
    private List<CartaDesarrollo> cartasDesarrollo;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.mapaRecursos = Recurso.crearMazoProduccionJugador();
        this.cartasDesarrollo = new ArrayList<>();
    }
    public int getCantidadDeRecursoEspecifico(String recurso){
        return Objects.requireNonNull(buscarRecurso(recurso)).getCantidad();

    }

    private Recurso buscarRecurso(String recursoBuscado) {
        for (String recurso : mapaRecursos.keySet()) {
            if (recurso.equals(recursoBuscado)) {
                return mapaRecursos.get(recurso);
            }
        }
        return null;
    }

    public Recurso elegirCartaRandomADescartar(){
        List<Recurso> disponibles = this.mapaRecursos.values().stream().filter(r -> r.tieneAlMenos(1)).toList();

        if (disponibles.isEmpty()) {
            System.out.println("No disponibles encontrados");
            return null;
        }

        Recurso elegido = disponibles.get(
                (int) (Math.random() * disponibles.size())
        );

        elegido.descartar(1);

        return elegido;
    }

    public void descartarMitad(){
        Banco banco = Banco.getBanco();
        if(this.cantidadCartas() > 7) {
            int cantidadADescartar = this.cantidadCartas() / 2;
            for (int i = 1; i <= cantidadADescartar; i++) {
                Recurso recursoDescartado = elegirCartaRandomADescartar();
                recursoDescartado.transferirAlBanco(banco, 1);
            }
        }
    }

    public void dejarseRobarPorJugador(Jugador jugador) {
        elegirCartaRandomADescartar().transferirAJugador(jugador, 1);
    }

    public int cantidadCartas(){
        int cantidad = 0;
        for (Map.Entry<String, Recurso> recurso : mapaRecursos.entrySet()) {
            cantidad = recurso.getValue().sumadorCantidad(cantidad);
        }
        return cantidad;
    }

    public void sumarPunto(){
        this.puntaje++;
    }

    public int calcularPuntaje() {
        int puntaje = this.puntaje;
        for(CartaDesarrollo carta :  this.cartasDesarrollo) {
            puntaje = carta.modificarPuntaje(puntaje);
        }
        //Sumar Cartas especiales
        return puntaje;
    }

    public void sumarRecurso(Piedra piedra, int cantidad){
        this.mapaRecursos.get("Piedra").sumar(cantidad);
    }

    public void sumarRecurso(Ladrillo ladrillo, int cantidad){
        this.mapaRecursos.get("Ladrillo").sumar(cantidad);
    }

    public void sumarRecurso(Oveja oveja, int cantidad){
        this.mapaRecursos.get("Oveja").sumar(cantidad);
    }

    public void sumarRecurso(Madera madera, int cantidad){
        this.mapaRecursos.get("Madera").sumar(cantidad);
    }

    public void sumarRecurso(Trigo trigo, int cantidad){
        this.mapaRecursos.get("Trigo").sumar(cantidad);
    }

    public void descartarRecurso(Trigo trigo, int cantidad){
        this.mapaRecursos.get("Trigo").descartar(cantidad);
    }

    public void descartarRecurso(Madera madera, int cantidad){
        this.mapaRecursos.get("Madera").descartar(cantidad);
    }

    public void descartarRecurso(Oveja oveja, int cantidad){
        this.mapaRecursos.get("Oveja").descartar(cantidad);
    }

    public void descartarRecurso(Piedra piedra, int cantidad){
        this.mapaRecursos.get("Piedra").descartar(cantidad);
    }

    public void descartarRecurso(Ladrillo ladrillo, int cantidad){
        this.mapaRecursos.get("Ladrillo").descartar(cantidad);
    }

    public boolean tieneAlMenos(Madera madera, int cantidad){
        return this.mapaRecursos.get("Madera").tieneAlMenos(cantidad);
    }

    public boolean tieneAlMenos(Ladrillo ladrillo, int cantidad){
        return this.mapaRecursos.get("Ladrillo").tieneAlMenos(cantidad);
    }

    public boolean tieneAlMenos(Piedra piedra, int cantidad){
        return this.mapaRecursos.get("Piedra").tieneAlMenos(cantidad);
    }

    public boolean tieneAlMenos(Oveja oveja, int cantidad){
        return this.mapaRecursos.get("Oveja").tieneAlMenos(cantidad);
    }

    public boolean tieneAlMenos(Trigo trigo, int cantidad){
        return this.mapaRecursos.get("Trigo").tieneAlMenos(cantidad);
    }

    public void pedirAlBanco(Trigo trigo, int cantidad){
        Banco banco =  Banco.getBanco();
        banco.jugadorLeSolicitaRecurso(this, trigo, cantidad);
    }

    public void pedirAlBanco(Madera madera, int cantidad){
        Banco banco =  Banco.getBanco();
        banco.jugadorLeSolicitaRecurso(this, madera, cantidad);
    }

    public void pedirAlBanco(Oveja oveja, int cantidad){
        Banco banco =  Banco.getBanco();
        banco.jugadorLeSolicitaRecurso(this, oveja, cantidad);
    }

    public void pedirAlBanco(Ladrillo ladrillo, int cantidad){
        Banco banco =  Banco.getBanco();
        banco.jugadorLeSolicitaRecurso(this, ladrillo, cantidad);
    }

    public void pedirAlBanco(Piedra piedra, int cantidad){
        Banco banco =  Banco.getBanco();
        banco.jugadorLeSolicitaRecurso(this, piedra, cantidad);
    }

    public void comerciarConJugador(Jugador jugador, List<Recurso> recursosOfertados, List<Recurso> recursosDeseados){
        System.out.println("Hola");
        if(tieneSuficientesParaOfertar(recursosOfertados)){
            System.out.println("Se envia oferta");
            jugador.recibirComercioConJugador(this, recursosDeseados, recursosOfertados);
        }
    }

    public void recibirComercioConJugador(Jugador jugador, List<Recurso> recursosQueEnviaria, List<Recurso> recursosQueRecibiria){
        if(tieneSuficientesParaOfertar(recursosQueEnviaria)){
            System.out.println("Se recibe oferta");
            jugador.sumarVariosRecursos(recursosQueEnviaria);
            jugador.restarVariosRecursos(recursosQueRecibiria);
            this.sumarVariosRecursos(recursosQueRecibiria);
            this.restarVariosRecursos(recursosQueEnviaria);
        }
    }

    private void sumarVariosRecursos(List<Recurso> recursosQueRecibe){
        for(Recurso recurso : recursosQueRecibe){
            recurso.transferirAJugador(this, recurso.sumadorCantidad(0));
        }
    }

    private void restarVariosRecursos(List<Recurso> recursosQueEnvia){
        for(Recurso recurso : recursosQueEnvia){
            recurso.descartarAJugador(this, recurso.sumadorCantidad(0));
        }
    }

    public boolean tieneSuficientesParaOfertar(List<Recurso> recursosOfertados){
        for (Recurso recurso : recursosOfertados){
            System.out.println("Jugador necesita: " + recurso.sumadorCantidad(0) + " " + recurso.nombre());
            System.out.println("Jugador tiene: " + mapaRecursos.get(recurso.nombre()).sumadorCantidad(0) + " " + recurso.nombre());
            if(!recurso.jugadorTieneAlMenos(this, recurso.sumadorCantidad(0))){
                return false;
            }
        }
        return true;
    }

    //comerciarConBanco( new Madera(4) )

    // Madera( 6 ) -> reglaDeComercio

    public void comerciarConBanco(Madera recursoOfertado, int cantidad, Recurso recursoDeseado){
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recursoOfertado);

        if (tieneSuficientesParaOfertar(recursos)){
            mapaRecursos.get("Madera").comerciarConBanco(this, cantidad, recursoDeseado);
        }

    }

    public void comerciarConBanco(Ladrillo recursoOfertado, int cantidad, Recurso recursoDeseado){
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recursoOfertado);

        if (tieneSuficientesParaOfertar(recursos)){
            mapaRecursos.get("Ladrillo").comerciarConBanco(this, cantidad, recursoDeseado);
        }

    }

    public void comerciarConBanco(Piedra recursoOfertado, int cantidad, Recurso recursoDeseado){
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recursoOfertado);

        if (tieneSuficientesParaOfertar(recursos)){
            mapaRecursos.get("Piedra").comerciarConBanco(this, cantidad, recursoDeseado);
        }

    }

    public void comerciarConBanco(Trigo recursoOfertado, int cantidad, Recurso recursoDeseado){
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recursoOfertado);

        if (tieneSuficientesParaOfertar(recursos)){
            mapaRecursos.get("Trigo").comerciarConBanco(this, cantidad, recursoDeseado);
        }

    }

    public void comerciarConBanco(Oveja recursoOfertado, int cantidad, Recurso recursoDeseado){
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recursoOfertado);

        if (tieneSuficientesParaOfertar(recursos)){
            mapaRecursos.get("Oveja").comerciarConBanco(this, cantidad, recursoDeseado);
        }

    }


    public void darReglaA(Madera madera, ReglaDeComercio reglaDeComercio) {
        this.mapaRecursos.get("Madera").cambiarRegla(reglaDeComercio);
    }

    public void darReglaA(Ladrillo ladrillo, ReglaDeComercio reglaDeComercio) {
        this.mapaRecursos.get("Ladrillo").cambiarRegla(reglaDeComercio);
    }

    public void darReglaA(Piedra piedra, ReglaDeComercio reglaDeComercio) {
        this.mapaRecursos.get("Piedra").cambiarRegla(reglaDeComercio);
    }

    public void darReglaA(Oveja oveja, ReglaDeComercio reglaDeComercio) {
        this.mapaRecursos.get("Oveja").cambiarRegla(reglaDeComercio);
    }

    public void darReglaA(Trigo trigo, ReglaDeComercio reglaDeComercio) {
        this.mapaRecursos.get("Trigo").cambiarRegla(reglaDeComercio);
    }

    public void darReglaATodos(ReglaDeComercio reglaDeComercio) {
        for (Recurso recurso : this.mapaRecursos.values()){
            recurso.cambiarRegla(reglaDeComercio);
        }
    }

    public void imprimirRecursos(){
        for (Recurso recurso : this.mapaRecursos.values()){
            System.out.println("Jugador tiene: " + recurso.sumadorCantidad(0) + " " + recurso.nombre());
        }
    }

    public String getNombre() {
        return nombre;
    }


}