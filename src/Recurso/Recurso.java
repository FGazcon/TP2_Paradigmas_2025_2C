package Recurso;

import Jugador.Jugador;

import java.util.List;
import java.util.Map;

public abstract class Recurso{
     Map<Class<? extends Recurso>, Integer> costoCiudad = Map.of(
             Piedra.class, 3,
             Trigo.class, 2
     );

    Map<Class<? extends Recurso>, Integer> costoPoblado = Map.of(
            Madera.class, 1,
            Ladrillo.class, 1,
            Oveja.class, 1,
            Trigo.class, 1
    );

    Map<Class<? extends Recurso>, Integer> costoCarretera = Map.of(
            Madera.class, 1,
            Ladrillo.class, 1
    );

    Map<Class<? extends Recurso>, Integer> costoDesarrollo = Map.of(
        Oveja.class, 1,
        Trigo.class, 1,
        Piedra.class, 1
    );



     abstract String nombre();

    public void construirCarretera(List<Recurso> recursos,Jugador jugador) {

            if(chequeoConstruccion(recursos,costoCarretera)){
                jugador.construirCarretera(costoCarretera);
            }


    }
    public void construirCiudad(List<Recurso> recursos,Jugador jugador) {
        if(chequeoConstruccion(recursos,costoCiudad)){
            jugador.construirCiudad(costoCiudad);
        }
        //si devolvemos new ciudad() hay que sacar las cartas de la lista y devolversela al banco.

    }
    public void construirDesarrollo(List<Recurso> recursos,Jugador jugador) {

        if(chequeoConstruccion(recursos,costoDesarrollo)){
            jugador.construirDesarrollo(costoDesarrollo);
        }

    }
    public void construirPoblado(List<Recurso> recursos,Jugador jugador) {
        if(chequeoConstruccion(recursos,costoPoblado)){
            jugador.construirPoblado(costoPoblado);
        }
    }
    private boolean chequeoConstruccion(List<Recurso> recursos,Map<Class<? extends Recurso>, Integer> costo){

        int cantidadRecursos = 0;
        for (var entry : costo.entrySet()) {
            Class<? extends Recurso> tipo = entry.getKey();
            int cantidad = entry.getValue();

            for (Recurso recurso : recursos) {
                if (tipo == recurso.getClass()) {
                    cantidadRecursos++;
                }
                if(cantidadRecursos < cantidad) {
                    return false;
                }
            }

        }
        return true;

    }
}

