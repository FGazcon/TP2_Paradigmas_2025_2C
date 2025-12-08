package view;

import model.Catan.Catan;



public class JuegoObserver implements Observador {

    private final Catan modelo;

    public JuegoObserver(Catan modelo) {
        this.modelo = modelo;
        modelo.agregarObservador(this);    // Se registra
    }


    @Override
    public void actualizar(Object evento) {

    }
}