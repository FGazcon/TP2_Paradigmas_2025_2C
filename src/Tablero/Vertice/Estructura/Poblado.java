package Tablero.Vertice.Estructura;

import Produccion.Recurso;

public class Poblado extends Estructura{

    @Override
    public void entregarRecursos(Recurso recurso) {
        this.jugador.pedirAlBanco(recurso);
    }

}
