package Produccion;

import Recurso.Recurso;
import Recurso.RecursoFactory;

public class Carta  {
    Recurso recurso;
    public void usar() {
        recurso.usar();
    }
    public Carta(String tipo){
        this.recurso = RecursoFactory.crearRecurso(tipo);
    }
}
