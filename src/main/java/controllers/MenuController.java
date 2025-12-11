package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import model.Catan.Catan;

public class MenuController {

    private Catan modelo;

    public void setModelo(Catan modelo) {
        this.modelo = modelo;
    }

    @FXML
    public void irARegistro(ActionEvent event) {
        cambiarEscena(event, "/fxml/registro.fxml");
    }

    @FXML
    public void salir(ActionEvent event) {
        System.exit(0);
    }

    private void cambiarEscena(ActionEvent event, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene nueva = new Scene(loader.load());

            //Object controller = loader.getController();


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(nueva);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}