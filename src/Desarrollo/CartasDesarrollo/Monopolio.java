package Desarrollo.CartasDesarrollo;

import java.util.ArrayList;
import java.util.List;

public class Monopolio extends CartaDesarrollo{

    //Debera tener acceso a todos los otros jugadores. Relacionarlos entre si.

    public static List<Monopolio> generarListaCartaDeDesarrollo(int cantidad) {
        List<Monopolio> lista = new ArrayList<Monopolio>();
        for (int i = 0; i < cantidad; i++) {
            lista.add(new Monopolio());
        }
        return lista;
    }

}
