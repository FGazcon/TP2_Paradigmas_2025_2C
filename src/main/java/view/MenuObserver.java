package view;

import model.Catan.Catan;

public class MenuObserver implements Observador {

    private final Catan modelo;

    public MenuObserver(Catan modelo) {
        this.modelo = modelo;
        modelo.agregarObservador(this);
    }
/*
    @Override
    public void actualizar() {
        System.out.println("El modelo cambió. (MenuObserver)");
    }*/

    @Override
    public void actualizar(Object evento) {

    }
}