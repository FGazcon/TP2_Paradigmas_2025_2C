package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Banco.Banco;
import model.Catan.TurnoGeneral;
import model.Dados.Dados;
import model.Catan.Catan;
import model.Recurso.*;
import model.Jugador.Jugador;
import model.Tablero.Arista.Arista;
import model.Tablero.Arista.Carretera;
import model.Tablero.Factory.Factory_MapaBasico;
import model.Tablero.Hexagono;
import model.Tablero.Vertice.Vertice;

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
    private TurnoGeneral turnoActual;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Llamar a una instancia de java.Tablero que contenga la lista de Hexagonos
        List<Hexagono> hexs = Factory_MapaBasico.crearHexagonosBasico();
        this.lblRecursos = crearMapRecursos();
    }
    public void init(Catan catan){
        this.catan = catan;
        this.jugadorActual = this.catan.getTurno().getJugadorActual();
        this.tableroModelo = this.catan.getTablero();
        this.jugadores = catan.getJugadores();
        // Turno inicial
        //this.turnoInicial = new TurnoInicial(catan, tableroModelo, jugadorActual, dados);

        setTurnoActual(this.catan.getTurno());
        System.out.println(turnoActual);
        modoActual = ModoJuego.CONSTRUIR_POBLADO;
        this.lblJugadorActual.setText(jugadorActual.getNombre()); // Ojo!

        lblTurno.setText("Turno Inicial de " + jugadorActual.getNombre());

        asignarCoordenadas(tableroModelo.getHexagonos());
        asignarCoordenadasVertices(tableroModelo);
        dibujarTablero(tableroModelo);
    }

    public void setValores(){
        this.jugadorActual = this.catan.getTurno().getJugadorActual();
        this.lblJugadorActual.setText(jugadorActual.getNombre());
        this.lblTurno.setText("Turno Inicial de " + jugadorActual.getNombre());
    }

    @Override
    protected void manejarClickVertice(Vertice v, Circle ui) {
        try {
            if (modoActual == ModoJuego.CONSTRUIR_POBLADO) {
                try {
                    turnoActual.construirPoblado(v.getNumeroDeVertice());
                    ui.setFill(Color.BLUE);
                } catch (Exception ex) {
                    System.err.println("Error construir poblado: " + ex.getMessage());
                }
            }

            if (modoActual == ModoJuego.CONSTRUIR_CIUDAD) {
                try {
                    turnoActual.construirCiudad(v.getNumeroDeVertice());
                    ui.setFill(Color.BLUE);
                } catch (Exception ex) {
                    System.err.println("Error construir ciudad: " + ex.getMessage());
                }
            }


        } catch (Exception ex) {
            System.err.println("Error en vértice: " + ex.getMessage());
        }

        modoActual = ModoJuego.SELECCIONAR_NADA;
    }
    @Override
    protected void manejarClickArista(Arista a, Line ui) {
        if (modoActual == ModoJuego.CONSTRUIR_CARRETERA) {
            try {
                int origen = a.getPar().getDestino().getNumeroDeVertice();
                int destino = a.getDestino().getNumeroDeVertice();
                turnoActual.construirCarretera(new int[]{origen, destino});
                ui.setStroke(Color.BLUE);
            } catch (Exception ex) {
                System.err.println("Error construir carretera: " + ex.getMessage());
            }
        }

        try {
            Carretera c = new Carretera(jugadorActual);
            a.ubicarCarretera(c, a.getNumeroDeVertices());

            ui.setStroke(Color.BLUE);
            ui.setOpacity(1);

        } catch (Exception ex) {
            System.err.println("Error en arista: " + ex.getMessage());
        }

        modoActual = ModoJuego.SELECCIONAR_NADA;
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
        lblValorDados.setText("java.Dados: " + tirada);
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