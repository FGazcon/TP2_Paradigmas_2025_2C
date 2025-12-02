package model.Tablero.Arista;

import model.Errores.AristaFueraDeAlcance;
import model.Jugador.Jugador;

public class AristaVacia extends EstadoArista{

    @Override
    public void ubicarCarretera(Carretera carretera, Arista arista) {

        if(arista.validarCarreteraPara(carretera.getJugador())){
            arista.ocuparse(carretera);
            arista.ocuparPar(carretera);
        } else {
            throw new AristaFueraDeAlcance();
        }

    }

    @Override
    public boolean esDe(Jugador jugador, Carretera carretera) {
        return false;
    }
}
