package view;

import model.Catan.Catan;

public class InicialObserver implements Observador {

    private final Catan modelo;

    public InicialObserver(Catan modelo) {
        this.modelo = modelo;
        modelo.agregarObservador(this);    // Se registra
    }

    @Override
    public void actualizar() {
        System.out.println("El modelo cambió. (InicialObserver)");
    }
}