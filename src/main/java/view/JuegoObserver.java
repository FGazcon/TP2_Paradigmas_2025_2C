package view;

import controllers.JuegoController;
import javafx.application.Platform;
import model.Catan.Catan;
import model.EventoCatan;

public class JuegoObserver implements Observador {

    private final Catan modelo;
    private final JuegoController controller;

    public JuegoObserver(Catan modelo, JuegoController controller) {
        this.modelo = modelo;
        this.controller = controller;

        modelo.agregarObservador(this);
    }

    @Override
    public void actualizar(Object evento) {

        if (!(evento instanceof EventoCatan tipo))
            return;

        Platform.runLater(() -> manejarEvento(tipo));
    }

    private void manejarEvento(EventoCatan e) {

        switch (e) {

            case DADOS_TIRADOS -> controller.actualizarVistaTrasDados();

            case RECURSOS_ENTREGADOS -> controller.actualizarRecursosYVista();

            case POBLADO_CONSTRUIDO -> controller.refrescarConstruccion();

            case CIUDAD_CONSTRUIDA -> controller.refrescarConstruccion();

            case CARRETERA_CONSTRUIDA -> controller.refrescarConstruccion();

            case LADRON_MOVIDO -> controller.refrescarLadron();

            case JUGADOR_ROBADO -> controller.actualizarRecursosYVista();

            case COMERCIO_CON_JUGADOR_REALIZADO -> controller.actualizarRecursosYVista();

            case COMERCIO_CON_BANCO_REALIZADO -> controller.actualizarRecursosYVista();

            case DESARROLLO_COMPRADO -> controller.actualizarRecursosYVista();

            case CAMBIO_TURNO_GENERAL -> controller.actualizarTurno();

            case GANADOR -> controller.mostrarGanador();

            default -> { }
        }
    }
}