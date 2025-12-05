package model.Puerto;

import model.Comercio.DosPorUno;
import model.Jugador.Jugador;
import model.Recurso.Recurso;

public class DosXUno extends Puerto {



    public DosXUno(Recurso recurso){
        this.recurso = recurso;
    }

    //new DosXUno(new Madera());

    public void darReglaAJugador(Jugador jugador){
        this.recurso.darReglaA(jugador, new DosPorUno());
    }



    //rotected abstract java.Recurso recursoAsociado();
/*
    @Override
    public ReglaDeComercio regla() {
        return new java.Comercio.DosPorUno(recursoAsociado());
    }*/
}