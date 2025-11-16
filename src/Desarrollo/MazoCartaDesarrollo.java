package Desarrollo;

import Desarrollo.CartasDesarrollo.*;

import java.util.ArrayList;
import java.util.List;

public class MazoCartaDesarrollo<T extends CartaDesarrollo> {

    private List<T> mazo;

    public MazoCartaDesarrollo(List<T> mazo) {
        this.mazo = mazo;
    }

    public static List<MazoCartaDesarrollo<? extends CartaDesarrollo>> generarMazosCartaDesarrolloBanco() {
        List<MazoCartaDesarrollo<? extends CartaDesarrollo>> lista = new ArrayList<>();
        lista.add(new MazoCartaDesarrollo(Caballero.generarListaCartaDeDesarrollo(14)));
        lista.add(new MazoCartaDesarrollo(ConstruccionDeCarreteras.generarListaCartaDeDesarrollo(2)));
        lista.add(new MazoCartaDesarrollo(Descubrimiento.generarListaCartaDeDesarrollo(2)));
        lista.add(new MazoCartaDesarrollo(Monopolio.generarListaCartaDeDesarrollo(2)));
        lista.add(new MazoCartaDesarrollo(PuntoDeVictoria.generarListaCartaDeDesarrollo(5)));
        return lista;
    }
}
