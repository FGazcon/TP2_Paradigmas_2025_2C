package model.Desarrollo.CartasDesarrollo;

import model.Jugador.Jugador;
import model.Recurso.Madera;
import model.Recurso.Recurso;

import java.util.List;

public class Descubrimiento extends CartaDesarrollo {

    //Debera tener acceso al Banco, o activar directamente desde el banco con el dato del jugador activo.

    public void activar(Jugador jugador, Recurso recurso1, Recurso recurso2) {
        recurso1.hacerQuejugadorSoliciteABanco(jugador, 1);
        recurso2.hacerQuejugadorSoliciteABanco(jugador, 1);
    }

    @Override
    public void activar(Jugador jugador){

    }

}
