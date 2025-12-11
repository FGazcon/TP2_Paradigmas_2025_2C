package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import view.GanadorObserver;
import model.Catan.Catan;

public class GanadorController {

    @FXML private Label lblGanador;
    @FXML private BorderPane rootGanador;

    private String nombreGanador;

    public void setGanador(String nombre) {
        this.nombreGanador = nombre;
        lblGanador.setText("Ganador: " + nombre);
    }

    public void init(Catan modelo) {
        new GanadorObserver(modelo, this);

        if (modelo.getGanador() != null)
            setGanador(modelo.getGanador().getNombre());
    }

    @FXML
    public void volverAlMenu(ActionEvent event) {
        cambiarEscena("/fxml/menu.fxml");
    }

    @FXML
    public void salir(ActionEvent event) {
        System.exit(0);
    }

    private void cambiarEscena(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene nueva = new Scene(loader.load());

            Stage stage = (Stage) rootGanador.getScene().getWindow();
            stage.setScene(nueva);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}