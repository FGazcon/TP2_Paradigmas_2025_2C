package Puerto;

import Comercio.TresPorUno;
import Jugador.Jugador;

public class TresXUno extends Puerto {

//    @Override
//    public ReglaDeComercio regla() {
//        return (ReglaDeComercio) new TresXUno();
//    }

    public void darReglaAJugador(Jugador jugador){
        this.recurso.darReglaA(jugador, new DosPorUno());
        jugador.darleReglaA(new TresPorUno());
    }
}