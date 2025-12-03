package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Banco.Banco;
import model.Dados.Dados;
import model.Catan.Catan;
import model.Recurso.*;
import model.Jugador.Jugador;
import model.Tablero.Factory.Factory_MapaBasico;
import model.Tablero.Hexagono;
import model.Tablero.Tablero;

import java.net.URL;
import java.util.*;

public class JuegoController extends BaseTableroController implements Initializable {

    @FXML private Label lblJugadorActual;
    @FXML private Label lblTurno;
    @FXML private Label lblValorDados;
    @FXML private Label lblPV;

    @FXML private Label lblInvMadera;
    @FXML private Label lblInvLadrillo;
    @FXML private Label lblInvOveja;
    @FXML private Label lblInvTrigo;
    @FXML private Label lblInvPiedra;

    @FXML private Pane tableroPane;
    @FXML private Button btnComercio;
    @FXML private Button btnDados;

    private Banco banco = new Banco();
    private Catan catan;
    private Dados dados = new Dados();
    private List<Jugador> jugadores = new ArrayList<>();
    private Jugador jugadorActual;
    private Map<Label, Recurso> lblRecursos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Llamar a una instancia de Tablero que contenga la lista de Hexagonos
        List<Hexagono> hexs = Factory_MapaBasico.crearHexagonosBasico();
        this.lblRecursos = crearMapRecursos() ;
        catan = new Catan(jugadores,banco);
        tableroModelo = catan.getTablero();

        jugadorActual = new Jugador("Jugador",banco);

        //turnoActual = new TurnoPersonal(catan, tableroModelo, jugadorActual, dados);

        asignarCoordenadas(tableroModelo.getHexagonos());

        asignarCoordenadasVertices(tableroModelo);

        dibujarTablero(tableroModelo);
    }

    private Map<Label, Recurso> crearMapRecursos() {
        Map<Label, Recurso> map = new HashMap<>();
        map.put(lblInvMadera, new Madera());
        map.put(lblInvPiedra, new Piedra());
        map.put(lblInvOveja, new Oveja());
        map.put(lblInvLadrillo, new Ladrillo());
        map.put(lblInvTrigo, new Trigo());
        return map;
    }

    private void actualizarRecursos() {
        for (var entry : lblRecursos.entrySet()) {
            entry.getKey().setText(
                    String.valueOf(jugadorActual.getCantidadDeRecursoEspecifico(entry.getValue()))
            );
        }
    }

    @FXML
    public void tirarDados() {
        int tirada = dados.tirarDados();
        lblValorDados.setText("Dados: " + tirada);
        actualizarRecursos();
    }

    @FXML
    public void construirPoblado() {
        modoActual = ModoJuego.CONSTRUIR_POBLADO;
    }

    @FXML
    public void construirCiudad() {
        modoActual = ModoJuego.CONSTRUIR_CIUDAD;
    }

    @FXML
    public void construirCarretera() {
        modoActual = ModoJuego.CONSTRUIR_CARRETERA;
    }

    @FXML
    public void terminarTurno() {
        System.out.println("Turno terminado.");
        btnDados.setDisable(false);
    }

    @FXML
    public void salir() {
        System.exit(0);
    }
}