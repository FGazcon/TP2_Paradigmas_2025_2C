package Desarrollo;

import Desarrollo.CartasDesarrollo.*;

import java.util.ArrayList;
import java.util.List;

public class MazoDesarrolloGeneral {

    private List<MazoCartaDesarrollo<? extends CartaDesarrollo>> mazos = new ArrayList<>();

    public static MazoDesarrolloGeneral generarMazoDesarrolloBanco(){
        MazoDesarrolloGeneral mazos = new MazoDesarrolloGeneral();
        mazos.mazos = MazoCartaDesarrollo.generarMazosCartaDesarrolloBanco();
        return mazos;
    }

}
