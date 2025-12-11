package view;

import controllers.InicialController;
import javafx.application.Platform;
import model.Catan.Catan;
import model.EventoCatan;

public class InicialObserver implements Observador {

    private final Catan modelo;
    private final InicialController controller;

    public InicialObserver(Catan modelo, InicialController controller) {
        this.modelo = modelo;
        this.controller = controller;

        modelo.agregarObservador(this);
    }

    @Override
    public void actualizar(Object evento) {

        if (!(evento instanceof EventoCatan ev)) return;

        Platform.runLater(() -> {

            switch (ev) {

                case POBLADO_INICIAL_CONSTRUIDO -> {
                    System.out.println("[Observer] Poblado inicial construido");
                    controller.uiPobladoInicialConstruido();
                }

                case CARRETERA_INICIAL_CONSTRUIDA -> {
                    System.out.println("[Observer] Carretera inicial construida");
                    controller.uiCarreteraInicialConstruida();
                }

                case CAMBIO_TURNO_INICIAL -> {
                    System.out.println("[Observer] Cambio de turno inicial");
                    controller.uiCambioTurnoInicial();
                }

                case FIN_TURNO_INICIAL -> {
                    System.out.println("[Observer] Fin del turno inicial → cambiar a JuegoController");
                    controller.uiFinTurnoInicial();
                }

                default -> {
                    // Nada
                }
            }
        });
    }
}