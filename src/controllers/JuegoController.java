package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class JuegoController {

    @FXML private Label lblJugadorActual;
    @FXML private Label lblTurno;
    @FXML private Label lblValorDados;
    @FXML private Label lblPV;

    @FXML private Label lblInvMadera;
    @FXML private Label lblInvLadrillo;
    @FXML private Label lblInvOveja;
    @FXML private Label lblInvTrigo;
    @FXML private Label lblInvPiedra;

    @FXML
    public void tirarDados(ActionEvent e) {
        lblValorDados.setText("Dados: " + ((int)(Math.random()*6+1)) + " + " + ((int)(Math.random()*6+1)));
    }

    @FXML
    public void construirCarretera(ActionEvent e) {
        System.out.println("Construir carretera (lógica pendiente)");
    }

    @FXML
    public void construirPoblado(ActionEvent e) {
        System.out.println("Construir poblado (lógica pendiente)");
    }

    @FXML
    public void construirCiudad(ActionEvent e) {
        System.out.println("Construir ciudad (lógica pendiente)");
    }

    @FXML
    public void abrirComercio(ActionEvent e) {
        System.out.println("Comercio (lógica pendiente)");
    }

    @FXML
    public void comprarDesarrollo(ActionEvent e) {
        System.out.println("Comprar carta de desarrollo (lógica pendiente)");
    }

    @FXML
    public void usarDesarrollo(ActionEvent e) {
        System.out.println("Usar carta de desarrollo (lógica pendiente)");
    }

    @FXML
    public void terminarTurno(ActionEvent e) {
        System.out.println("Turno terminado");
    }

    @FXML
    public void salir(ActionEvent e) {
        System.exit(0);
    }
}