package Produccion;

import Recurso.Recurso;
import Recurso.RecursoFactory;

public class Carta  {
    Recurso recurso;
    public void usar() {
        recurso.usar();
    }
    public boolean cartaEncontrada(Recurso recursoBuscado){
        if (recurso.equals(recursoBuscado)){
            return true;
        }
        return false;
    }
    public Carta(String tipo){
        this.recurso = RecursoFactory.crearRecurso(tipo);
    }
}
