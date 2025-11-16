package Desarrollo.CartasDesarrollo;

import java.util.ArrayList;
import java.util.List;

public class Caballero extends CartaDesarrollo{

    //Debera tener acceso al Gestor de Ladron.

    public static List<Caballero> generarListaCartaDeDesarrollo(int cantidad) {
        List<Caballero> lista = new ArrayList<Caballero>();
        for (int i = 0; i < cantidad; i++) {
            lista.add(new Caballero());
        }
        return lista;
    }

}
