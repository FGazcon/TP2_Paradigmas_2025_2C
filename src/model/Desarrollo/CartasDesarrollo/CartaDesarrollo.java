package model.Desarrollo.CartasDesarrollo;

import model.Tablero.Tablero;
import model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo.EstadoCartaDesarrollo;
import model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo.RecienComprada;
import model.Jugador.Jugador;
import model.Recurso.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CartaDesarrollo {

    private EstadoCartaDesarrollo estado;
    private static List<Recurso> costo;

    public CartaDesarrollo(){
        this.estado = new RecienComprada();
        this.costo = new ArrayList<>();

        this.costo.add(new Piedra(1));
        this.costo.add(new Oveja(1));
        this.costo.add(new Trigo(1));
    }

    public abstract ActivacionDesarrollo prepararActivacion();

    public void pasarTurnoDeCompra(){
        this.estado.pasarTurnoDeCompra(this);
    }

    public void setEstado(EstadoCartaDesarrollo estado){
        this.estado = estado;
    }

    public ActivacionDesarrollo intentarActivarse(){
        return this.estado.intentarActivarse(this);
    }

    public int modificarPuntaje(int puntaje){
        return puntaje;
    }

    public static boolean jugadorMePuedePagar(Jugador jugador){
        return jugador.tieneSuficientesParaOfertar(costo);
    }

    public void cobrarleAJugador(Jugador jugador){
        jugador.pagarleAlBanco(this.costo);
    }

    public List<CartaDesarrollo> sumarActivable(List<CartaDesarrollo> cartasDesarrollo){
        return this.estado.sumarActivable(cartasDesarrollo, this);
    }

    public List<CartaDesarrollo> sumarUsada(List<CartaDesarrollo> cartasDesarrollo){
        return this.estado.sumarUsada(cartasDesarrollo, this);
    }

    public List<CartaDesarrollo> sumarRecienComprada(List<CartaDesarrollo> cartasDesarrollo){
        return this.estado.sumarRecienComprada(cartasDesarrollo, this);
    }

}
