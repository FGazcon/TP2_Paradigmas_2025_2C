package Produccion;

import Recurso.Recurso;
import Recurso.RecursoFactory;

import java.util.Collections;
import java.util.List;

public class Carta  {
    Recurso recurso;

    public static List<Carta> mezclarCartas(List<Carta> cartas){
        Collections.shuffle(cartas);
        return cartas;
    }

    public void usar() {
        recurso.nombre();
    }

    public boolean cartaEncontrada(Recurso recursoBuscado){
        return recurso.nombre().equals(recursoBuscado.nombre());
    }
    
    public Carta(){
        this.recurso  = null;
    }

    public Carta(String tipo){
        this.recurso = RecursoFactory.crearRecurso(tipo);
    }

    public Recurso getRecurso(){
        return this.recurso;
    }

}
