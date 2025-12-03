package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Banco.Banco;
import model.Catan.Catan;
import model.Dados.Dados;
import model.Jugador.Jugador;
import model.Recurso.*;
import model.Tablero.Factory.Factory_MapaBasico;
import model.Tablero.Hexagono;
import model.Tablero.Tablero;

import java.net.URL;
import java.util.*;

public class InicialController extends BaseTableroController implements Initializable {

    @FXML private Label lblTurnoInicial;   // "Turno inicial de: Jugador ..."
    @FXML private Pane tableroPane;
    @FXML private Button btnSiguiente;

    private Catan catan;
    private List<Jugador> jugadores = new ArrayList<>();
    private int indiceJugador = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.jugadores = jugadores;
        // Modelo (luego Registro enviará los jugadores reales)
        //jugadores = crearJugadoresDummyParaTest();
        jugadorActual = catan.getTurno().getJugadorActual();

        lblTurnoInicial.setText("Turno inicial de: " + jugadorActual.getNombre());

        catan = new Catan(jugadores, new Banco());
        tableroModelo = catan.getTablero();

        asignarCoordenadas(tableroModelo.getHexagonos());
        asignarCoordenadasVertices(tableroModelo);

        dibujarTablero(tableroModelo);

        // En el turno inicial SOLO se construyen poblados
        modoActual = ModoJuego.CONSTRUIR_POBLADO;
    }

    @FXML
    public void siguiente() {
        indiceJugador++;

        if (indiceJugador >= jugadores.size()) {
            System.out.println("Fin de los turnos iniciales → ahora va juego normal");
            volverAJuego();
            return;
        }

        jugadorActual = jugadores.get(indiceJugador);
        lblTurnoInicial.setText("Turno inicial de: " + jugadorActual.getNombre());

        modoActual = ModoJuego.CONSTRUIR_POBLADO;
        System.out.println("El siguiente jugador debe elegir un vértice.");
    }

    private void volverAJuego() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/fxml/juego.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(loader.load());
            javafx.stage.Stage stage = (javafx.stage.Stage) tableroPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}