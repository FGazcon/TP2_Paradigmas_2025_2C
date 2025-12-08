package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.TurnoGeneral;
import model.Dados.Dados;
import model.Jugador.Jugador;
import model.Recurso.*;
import model.Tablero.Arista.Arista;
import model.Tablero.Arista.Carretera;
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

    @FXML private Button btnComercio;
    @FXML private Button btnDados;

    private Banco banco = new Banco();
    private Catan catan;
    private Dados dados = new Dados();
    private List<Jugador> jugadores = new ArrayList<>();
    private Jugador jugadorActual;
    private Map<Label, Recurso> lblRecursos;
    private TurnoGeneral turnoActual;
    private Hexagono hexagonoPrevioLadron = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Llamar a una instancia de java.Tablero que contenga la lista de Hexagonos
        this.lblRecursos = crearMapRecursos();
    }
    public void init(Catan catan, Pane tablero){
        this.catan = catan;
        this.jugadorActual = this.catan.getTurno().getJugadorActual();
        this.tableroModelo = this.catan.getTablero();
        this.jugadores = catan.getJugadores();
        this.tableroPane.getChildren().add(tablero);

        // Turno inicial
        //this.turnoInicial = new TurnoInicial(catan, tableroModelo, jugadorActual, dados);

        this.turnoActual = catan.getTurno().getTurnoGeneral();
        System.out.println(turnoActual);
        //modoActual = ModoJuego.CONSTRUIR_POBLADO;
        setValores();

    //hay q cargar  el mapa ya pintado
        //asignarCoordenadas(tableroModelo.getHexagonos());
       // asignarCoordenadasVertices(tableroModelo);

    }
    public void setValores(){
        this.jugadorActual = this.catan.getTurno().getJugadorActual();
        this.lblJugadorActual.setText(jugadorActual.getNombre());
        this.lblTurno.setText("Turno de " + jugadorActual.getNombre());
        actualizarPuntos();
        actualizarRecursos();
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
    public void cambiarLadronImagen() {
        Hexagono hex = this.tableroModelo.getLadron().getHexagonoBajoAtaque();

        pintarHexagonoLadron(hex);
    }

    @FXML
    public void tirarDados() {
        int tirada = dados.tirarDados();
        lblValorDados.setText("Dados: " + tirada);
        //btnDados.setDisable(true);
        this.turnoActual.tirarDados(tirada);

        if (tirada == 7) {
            lblTurno.setText("Mové el ladrón seleccionando un hexágono.");
            this.modoActual = ModoJuego.MODO_MOVER_LADRON;
            //cambiarLadronImagen();
        }

        actualizarRecursos();
        actualizarPuntos();

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
/*
    @FXML
    public void terminarTurno() {
        System.out.println("Turno terminado.");
    }*/

    @FXML
    public void salir() {
        System.exit(0);
    }

    public void actualizarPuntos(){
        lblPV.setText("PV: " + jugadorActual.calcularPuntaje() + " / 10");

    }


    @FXML
    public void terminarTurno(ActionEvent event) {
        System.out.println("Turno terminado.");
        System.out.println(this.turnoActual);
        btnDados.setDisable(false);
        this.catan.terminarTurno();
        this.turnoActual= catan.getTurno().getTurnoGeneral();
        setValores();

        // TODO: aca llamás al administrador de jugadores y verificas si pasas al turno general
    }

    public void pintarHexagonoLadron(Hexagono hex) {

        // 1. Despintar hexágono anterior
        if (hexagonoPrevioLadron != null) {
            Shape uiPrevio = uiHexagonos.get(hexagonoPrevioLadron);
            if (uiPrevio != null) {
                uiPrevio.setFill(Color.BEIGE);
                uiPrevio.setOpacity(1);
                uiPrevio.setStroke(Color.DARKGRAY);
                uiPrevio.setStrokeWidth(1.5);
            }
        }

        // 2. Pintar hexágono actual
        Shape uiActual = uiHexagonos.get(hex);
        if (uiActual != null) {
            uiActual.setFill(Color.DARKRED);
            uiActual.setOpacity(0.75);
            uiActual.setStroke(Color.BLACK);
            uiActual.setStrokeWidth(3);
        }

        // 3. Guardar referencia para la próxima
        hexagonoPrevioLadron = hex;
    }
    @Override
    protected void manejarClickHexagono(Hexagono h) {
        System.out.println("cambiar ladron");
        if (modoActual == ModoJuego.MODO_MOVER_LADRON) {
            moverLadronAHexagono(h);
        }
    }

    private void moverLadronAHexagono(Hexagono nuevoHex) {
        // 1. Mover ladrón en el modelo
        turnoActual.moverLadron(nuevoHex.getNumero());

        // 2. Pintar en la UI
        pintarHexagonoLadron(nuevoHex);

        // 3. Salir del modo
        modoActual = ModoJuego.SELECCIONAR_NADA;

        // 4. (Opcional) Mostrar mensaje
        lblTurno.setText("Ladrón movido. Continuá tu turno.");
    }


}