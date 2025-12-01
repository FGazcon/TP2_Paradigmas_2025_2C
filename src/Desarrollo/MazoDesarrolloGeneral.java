package Desarrollo;

import Desarrollo.CartasDesarrollo.*;

import java.util.*;

public class MazoDesarrolloGeneral {

    private List<CartaDesarrollo> mapaCartaDesarrollo;

    public static MazoDesarrolloGeneral generarMazoDesarrolloBanco(){
        MazoDesarrolloGeneral mazos = new MazoDesarrolloGeneral();
        mazos.mapaCartaDesarrollo.addAll(mazoCaballeros());
        mazos.mapaCartaDesarrollo.addAll(mazoPuntosDeVictoria());
        mazos.mapaCartaDesarrollo.addAll(mazoConstruccionCarreteras());
        mazos.mapaCartaDesarrollo.addAll(mazoDescubrimiento());
        mazos.mapaCartaDesarrollo.addAll(mazoMonopolio());
        System.out.println("Mazo desarrollo generado " + mazos.mapaCartaDesarrollo.size());
        return mazos;
    }

    public MazoDesarrolloGeneral(){
        mapaCartaDesarrollo = new ArrayList<>();
    }

    public CartaDesarrollo pedirRecurso(){

        Random rnd = new Random();
        int nro = rnd.nextInt(0, 4);

        return mapaCartaDesarrollo.get(nro);

    }

    public static List<CartaDesarrollo> mazoCaballeros() {
        List<CartaDesarrollo> caballeros= new ArrayList<>();
        for(int i=0;i<14;i++){
            caballeros.add(new Caballero());
        }
        return caballeros;
    }

    public static List<CartaDesarrollo> mazoPuntosDeVictoria() {
        List<CartaDesarrollo> puntos= new ArrayList<>();
        for(int i=0;i<5;i++){
            puntos.add(new PuntoDeVictoria());
        }
        return puntos;
    }

    public static List<CartaDesarrollo> mazoDescubrimiento() {
        List<CartaDesarrollo> descubrimiento= new ArrayList<>();
        for(int i=0;i<2;i++){
            descubrimiento.add(new Descubrimiento());
        }
        return descubrimiento;
    }

    public static List<CartaDesarrollo> mazoConstruccionCarreteras() {
        List<CartaDesarrollo> carreteras= new ArrayList<>();
        for(int i=0;i<2;i++){
            carreteras.add(new ConstruccionDeCarreteras());
        }
        return carreteras;
    }

    public static List<CartaDesarrollo> mazoMonopolio() {
        List<CartaDesarrollo> monopolios= new ArrayList<>();
        for(int i=0;i<2;i++){
            monopolios.add(new Monopolio());
        }
        return monopolios;
    }

}
