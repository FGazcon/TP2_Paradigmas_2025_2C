package Puerto;

import Comercio.DosPorUno;
import Jugador.Jugador;
import Recurso.Recurso;

public class DosXUno extends Puerto {



    public DosXUno(Recurso recurso){
        this.recurso = recurso;
    }

    //new DosXUno(new Madera());

    public void darReglaAJugador(Jugador jugador){
        this.recurso.darReglaA(jugador, new DosPorUno());
    }



    //rotected abstract Recurso recursoAsociado();
/*
    @Override
    public ReglaDeComercio regla() {
        return new Comercio.DosPorUno(recursoAsociado());
    }*/
}