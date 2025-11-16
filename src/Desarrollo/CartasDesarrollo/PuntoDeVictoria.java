package Desarrollo.CartasDesarrollo;

import java.util.ArrayList;
import java.util.List;

public class PuntoDeVictoria extends CartaDesarrollo{

    //Debera tener acceso al conteo de puntos
    //Podria no implementarse, y directamente sumar cuando se saque del Banco.

    public static List<PuntoDeVictoria> generarListaCartaDeDesarrollo(int cantidad) {
        List<PuntoDeVictoria> lista = new ArrayList<PuntoDeVictoria>();
        for (int i = 0; i < cantidad; i++) {
            lista.add(new PuntoDeVictoria());
        }
        return lista;
    }

}
