package Desarrollo.CartasDesarrollo;

import Catan.Turno;
import Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo.EstadoCartaDesarrollo;
import Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo.RecienComprada;
import Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

public abstract class CartaDesarrollo {

    private EstadoCartaDesarrollo estado;

    public CartaDesarrollo(){
        this.estado = new RecienComprada();
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

}
