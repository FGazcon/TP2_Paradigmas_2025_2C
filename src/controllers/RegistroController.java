package controllers;

import Jugador.Jugador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class RegistroController {

    @FXML private TextField txtJugador1;
    @FXML private TextField txtJugador2;
    @FXML private TextField txtJugador3;
    @FXML private TextField txtJugador4;

    @FXML
    public void comenzarPartida(ActionEvent event) {
        List<String> nombres = new ArrayList<>();

        nombres.add(txtJugador1.getText().trim());
        nombres.add(txtJugador2.getText().trim());
        nombres.add(txtJugador3.getText().trim());
        nombres.add(txtJugador4.getText().trim());

        // Validación simple
        if (nombres.stream().anyMatch(n -> n.isEmpty())) {
            System.out.println("Faltan nombres!");
            return;
        }

        // TODO: Crear jugadores reales y pasarlos al juego
        System.out.println("Jugadores registrados: " + nombres);

        cambiarEscena(event, "/juego.fxml");
    }

    @FXML
    public void salir(ActionEvent event) {
        System.exit(0);
    }

    private void cambiarEscena(ActionEvent event, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene nueva = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(nueva);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}