package model.Tablero.Puerto;

import model.Comercio.TresPorUno;
import model.Jugador.Jugador;

public class TresXUno extends Puerto {

//    @Override
//    public ReglaDeComercio regla() {
//        return (ReglaDeComercio) new TresXUno();
//    }

    public TresXUno(){
        this.nombre = "3x1";
    }
    public void darReglaAJugador(Jugador jugador){
        jugador.darReglaATodos(new TresPorUno());
    }


}