package model.Desarrollo.CartasDesarrollo;

import model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo.EstadoCartaDesarrollo;
import model.Desarrollo.CartasDesarrollo.EstadoCartaDesarrollo.RecienComprada;
import model.Jugador.Jugador;
import model.Recurso.Recurso;

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
