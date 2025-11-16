package Desarrollo.CartasDesarrollo;

import java.util.ArrayList;
import java.util.List;

public class ConstruccionDeCarreteras extends CartaDesarrollo {

    //Debera tener acceso al Mapa o el gestor de Construccion

    public static List<ConstruccionDeCarreteras> generarListaCartaDeDesarrollo(int cantidad) {
        List<ConstruccionDeCarreteras> lista = new ArrayList<ConstruccionDeCarreteras>();
        for (int i = 0; i < cantidad; i++) {
            lista.add(new ConstruccionDeCarreteras());
        }
        return lista;
    }

}
