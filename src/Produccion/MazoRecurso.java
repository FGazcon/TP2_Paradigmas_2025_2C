package Produccion;

public class MazoRecurso {

    private int cantidadRecurso;
    private Recurso tipo;

    public MazoRecurso(Recurso tipo) {
        this.tipo = tipo;
    }

    public MazoRecurso(Recurso tipo, int cantidadRecurso) {
        this.tipo = tipo;
        this.cantidadRecurso = cantidadRecurso;
    }

    public boolean esDeTipo(Recurso tipo){
        return this.tipo.nombre() == tipo.nombre();
    }

    public boolean darRecurso(){
        if (cantidadRecurso > 0){
            cantidadRecurso--;
            return true;
        }
        return false;
    }

    public void recibirRecurso(){
        this.cantidadRecurso++;
    }
}
