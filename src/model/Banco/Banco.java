package model.Banco;

import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
import model.Desarrollo.MazoDesarrolloGeneral;
import model.Errores.NoQuedanMasDesarrollo;
import model.Jugador.Jugador;
import model.Recurso.*;

import java.util.List;
import java.util.Map;

public class Banco {

    private MazoDesarrolloGeneral mazoDesarrollo;
    private Map<String, Recurso> mazoDeProduccion;

    public Banco() {
        this.mazoDeProduccion = Recurso.crearMazoProduccionBanco();
        this.mazoDesarrollo = MazoDesarrolloGeneral.generarMazoDesarrolloBanco();
    }

    public void sumarRecurso(Recurso recurso, int cantidad) {
        recurso.getRecursoBanco(this).sumar(cantidad);
    }

    public void descartarRecurso(Recurso recurso, int cantidad) {
        recurso.getRecursoBanco(this).descartar(cantidad);
    }

    public boolean tieneAlMenos(Recurso recurso, int cantidad) {
        return recurso.getRecursoBanco(this).tieneAlMenos(cantidad);
    }

    /*
     * public Recurso encontrarRecurso(Recurso recurso){
     *
     * }
     *
     * */


/*
    public void jugadorLeSolicitaRecurso(Jugador jugador,Recurso recurso, int cantidad){
        if(recurso.tieneAlMenos(cantidad)){
            recurso.transferirAJugador(jugador,cantidad);
            recurso.descartarAlBanco(this,cantidad);
        }*/


    public void jugadorLeSolicitaRecurso(Jugador jugador, Recurso recurso, int cantidad){
        Recurso recursoPropio = recurso.getRecursoBanco(this);
        if(recursoPropio.tieneAlMenos(cantidad)){
            recursoPropio.transferirAJugador(jugador, cantidad);
            recursoPropio.descartarAlBanco(this, cantidad);
        }
    }



    public void jugadorQuiereIntercambiar(Jugador jugador, Recurso recursoOfrecido, int cantidad, Recurso recursoDeseado){
        Recurso recursoPropio = recursoDeseado.getRecursoBanco(this);

        if(recursoPropio.bancoTieneAlMenos(this, cantidad)){

            recursoOfrecido.descartarAJugador(jugador, cantidad);
            recursoOfrecido.transferirAlBanco(this, cantidad);

            recursoDeseado.transferirAJugador(jugador, cantidad);
            recursoDeseado.descartarAlBanco(this, cantidad);

        }

    }

    public Recurso getMadera(){
        return this.mazoDeProduccion.get("Madera");
    }

    public Recurso getLadrillo(){
        return this.mazoDeProduccion.get("Ladrillo");
    }

    public Recurso getPiedra(){
        return this.mazoDeProduccion.get("Piedra");
    }

    public Recurso getOveja(){
        return this.mazoDeProduccion.get("Oveja");
    }

    public Recurso getTrigo(){
        return this.mazoDeProduccion.get("Trigo");
    }

    public void sumarVariosRecursos(List<Recurso> recursosQueRecibe){
        for(Recurso recurso : recursosQueRecibe){
            recurso.transferirAlBanco(this, recurso.sumadorCantidad(0));
        }
    }

    public void jugadorSolicitaDesarrollo(Jugador jugador){

        try{
            CartaDesarrollo carta = this.mazoDesarrollo.jugadorSolicitaDesarrollo();
            jugador.registrarDesarrollo(carta);
            carta.cobrarleAJugador(jugador);
        } catch(NoQuedanMasDesarrollo e){
            System.out.println("No Quedan mas desarrollo");
        }

    }
}
