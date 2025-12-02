package model.Tablero;


import model.Errores.HexagonoBajoAsalto;

public class BajoAsalto extends EstadoHexagono{

    public void intentarProducir(Hexagono hexagono){
        System.out.println("Bajo Asalto");
        throw new HexagonoBajoAsalto();
    }

}
