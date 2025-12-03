package model.Desarrollo.CartasDesarrollo;

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

        this.costo.add(new Piedra());
        this.costo.add(new Oveja());
        this.costo.add(new Trigo());
    }

    public void pasarTurnoDeCompra(){
        this.estado.pasarTurnoDeCompra(this);
    }

    public void setEstado(EstadoCartaDesarrollo estado){
        this.estado = estado;
    }

    public void intentarActivarse(Jugador jugador){
        this.estado.intentarActivarse(this, jugador);
    }

    public abstract void activar(Jugador jugador);

    public int modificarPuntaje(int puntaje){
        return puntaje;
    }

    public static boolean jugadorMePuedePagar(Jugador jugador){
        return jugador.tieneSuficientesParaOfertar(costo);
    }

}
