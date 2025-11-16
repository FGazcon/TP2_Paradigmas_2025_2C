package Tablero.Vertice.Estructura;

import Produccion.Recurso;

public class Ciudad extends Estructura {

    @Override
    public void entregarRecursos(Recurso recurso) {
        this.jugador.pedirAlBanco(recurso);
        this.jugador.pedirAlBanco(recurso);
    }
}
