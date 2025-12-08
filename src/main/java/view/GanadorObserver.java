package view;

import controllers.GanadorController;
import javafx.application.Platform;
import model.Catan.Catan;
import model.EventoCatan;

public class GanadorObserver implements Observador {

    private final Catan modelo;
    private final GanadorController controller;

    public GanadorObserver(Catan modelo, GanadorController controller) {
        this.modelo = modelo;
        this.controller = controller;

        modelo.agregarObservador(this);
    }

    @Override
    public void actualizar(Object evento) {
        if (evento == EventoCatan.GANADOR) {

            Platform.runLater(() -> {
                if (modelo.getGanador() != null) {
                    controller.setGanador(modelo.getGanador().getNombre());
                }
            });

        }
    }
}