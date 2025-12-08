package model.Jugador;

import model.Banco.Banco;
import model.Comercio.ReglaDeComercio;
import model.Desarrollo.CartasDesarrollo.ActivacionDesarrollo;
import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jugador {

    private String nombre;
    private int puntaje;
    private Map<String, Recurso> mapaRecursos;
    private List<CartaDesarrollo> cartasDesarrollo;
    private Banco banco;
    private int cantidadCabelleros;

    public Jugador(String nombre, Banco banco) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.mapaRecursos = Recurso.crearMazoProduccionJugador();
        this.cartasDesarrollo = new ArrayList<>();
        this.banco = banco;
    }

    public int getCantidadDeRecursoEspecifico(Recurso recurso){
        return recurso.getRecursoJugador(this).getCantidad();
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
        for (Recurso recurso : mapaRecursos.values()) {
            cantidad = recurso.sumadorCantidad(cantidad);
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

    public void sumarRecurso(Recurso recurso, int cantidad) {
        recurso.sumarCantidadDeUnRecursoAJugador(this, cantidad);
    }

    public void descartarRecurso(Recurso recurso, int cantidad) {
        recurso.descartarCantidadDeUnRecursoAJugador(this, cantidad);
    }

    public boolean tieneAlMenos(Recurso recurso, int cantidad) {
        return recurso.verificarSiJugadorTieneAlMenosUnaCantidadDeUnRecurso(this, cantidad);
    }

    public void pedirAlBanco(Recurso recurso, int cantidad){
        banco.jugadorLeSolicitaRecurso(this, recurso.obtenerRecursoDeJugador(this), cantidad);
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
            if(!recurso.jugadorTieneAlMenos(this, recurso.sumadorCantidad(0))){
                return false;
            }
        }
        return true;
    }

    //comerciarConBanco( new Madera(4) )

    // Madera( 6 ) -> reglaDeComercio
    

    public void comerciarConBanco(Recurso recursoOfertado, int cantidad, Recurso recursoDeseado){
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recursoOfertado);

        if (tieneSuficientesParaOfertar(recursos)){
            recursoOfertado.comerciarRecursoDeUnJugadorConElBanco(this, cantidad, recursoDeseado, banco);
        }
    }

    public void darReglaA(Recurso recurso, ReglaDeComercio reglaDeComercio) {
        recurso.cambiarReglaDeComercioDeUnJugador(this, reglaDeComercio);
    }

    public void darReglaATodos(ReglaDeComercio reglaDeComercio) {
        for (Recurso recurso : this.mapaRecursos.values()){
            recurso.cambiarReglaDeComercioDeUnJugador(this, reglaDeComercio);
        }
    }

    public void imprimirRecursos(){
        for (Recurso recurso : this.mapaRecursos.values()){
            System.out.println("java.Jugador tiene: " + recurso.sumadorCantidad(0) + " " + recurso.nombre());
        }
    }

    public Recurso obtenerRecurso(String nombre) {
        return mapaRecursos.get(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public Recurso getMadera(){
        return this.obtenerRecurso("Madera");
    }

    public Recurso getLadrillo(){
        return this.obtenerRecurso("Ladrillo");
    }

    public Recurso getPiedra(){
        return this.obtenerRecurso("Piedra");
    }

    public Recurso getOveja(){
        return this.obtenerRecurso("Oveja");
    }

    public Recurso getTrigo(){
        return this.obtenerRecurso("Trigo");
    }

    public void pagarleAlBanco(List<Recurso> costo){
        restarVariosRecursos(costo);
        this.banco.sumarVariosRecursos(costo);
    }

    public void adquirirDesarrollo(){
        this.banco.jugadorSolicitaDesarrollo(this);
    }

    public void registrarDesarrollo(CartaDesarrollo carta){
        this.cartasDesarrollo.add(carta);
    }

    public List<CartaDesarrollo> getCartasDesarrolloSinActivar(){
        List<CartaDesarrollo> cartasDesarrollo = new ArrayList<>();
        for(CartaDesarrollo carta : this.cartasDesarrollo){
            carta.sumarActivable(cartasDesarrollo);
        }
        return cartasDesarrollo;
    }

    public List<CartaDesarrollo> getCartasDesarrolloUsadas(){
        List<CartaDesarrollo> cartasDesarrollo = new ArrayList<>();
        for(CartaDesarrollo carta : this.cartasDesarrollo){
            carta.sumarUsada(cartasDesarrollo);
        }
        return cartasDesarrollo;
    }

    public List<CartaDesarrollo> getCartasDesarrolloRecienCompradas(){
        List<CartaDesarrollo> cartasDesarrollo = new ArrayList<>();
        for(CartaDesarrollo carta : this.cartasDesarrollo){
            carta.sumarRecienComprada(cartasDesarrollo);
        }
        return cartasDesarrollo;
    }

    public void actualizarTurnoCartas(){
        for (CartaDesarrollo carta : this.cartasDesarrollo){
            carta.pasarTurnoDeCompra();
        }
    }

    private CartaDesarrollo obtenerCartaDesarrolloEn(int posicion) {
        return getCartasDesarrolloSinActivar().get(posicion);
    }

    private ActivacionDesarrollo activarCarta(CartaDesarrollo carta) {
        return carta.intentarActivarse();
    }

    public ActivacionDesarrollo getActivacionParaCartaEnPosicion(int posicion) {
        CartaDesarrollo carta = obtenerCartaDesarrolloEn(posicion);
        return activarCarta(carta);
    }

    public int getPuntos(){
        return this.puntaje;
    }

}