package Desarrollo.CartasDesarrollo;

import java.util.ArrayList;
import java.util.List;

public class Descubrimiento extends CartaDesarrollo {

    //Debera tener acceso al Banco, o activar directamente desde el banco con el dato del jugador activo.

    public static List<Descubrimiento> generarListaCartaDeDesarrollo(int cantidad) {
        List<Descubrimiento> lista = new ArrayList<Descubrimiento>();
        for (int i = 0; i < cantidad; i++) {
            lista.add(new Descubrimiento());
        }
        return lista;
    }

}
