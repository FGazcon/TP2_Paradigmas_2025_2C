package model;

import view.Observador;
import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    protected void notificar() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }
}