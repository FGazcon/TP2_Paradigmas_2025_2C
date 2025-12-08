package view;

import model.Catan.Catan;

public class RegistroObserver implements Observador {

    private final Catan modelo;

    public RegistroObserver(Catan modelo) {
        this.modelo = modelo;
        modelo.agregarObservador(this);
    }

    @Override
    public void actualizar(Object evento) {

    }
}
