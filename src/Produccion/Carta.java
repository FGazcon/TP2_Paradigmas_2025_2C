package Produccion;

import Recurso.Recurso;
import Recurso.RecursoFactory;

public class Carta  {
    Recurso recurso;
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
}
