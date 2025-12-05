package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.Turno;
import model.Catan.TurnoGeneral;
import model.Catan.TurnoInicial;
import model.Dados.Dados;
import model.Jugador.Jugador;
import model.Tablero.Arista.Arista;
import model.Tablero.Factory.Factory_MapaBasico;
import model.Tablero.Hexagono;
import model.Tablero.Vertice.Vertice;

import java.net.URL;
import java.util.*;

public class InicialController extends BaseTableroController implements Initializable {

    @FXML private Label lblJugadorActual;
    @FXML private Label lblTurno;

    @FXML private Pane tableroPane;
    //@FXML private Button btnCarretera;
   // @FXML private Button btnPoblado;

    private Banco banco = new Banco();
    private Catan catan;
    private Dados dados = new Dados();
    private List<Jugador> jugadores = new ArrayList<>();
    private Jugador jugadorActual;
    private TurnoInicial turnoInicial;
    private Turno turno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Crear tablero
        List<Hexagono> hexs = Factory_MapaBasico.crearHexagonosBasico();






    }
    public void init(Catan catan){
        this.catan = catan;
        this.jugadorActual = this.catan.getTurno().getJugadorActual();
        this.tableroModelo = this.catan.getTablero();
        this.jugadores = catan.getJugadores();
        // Turno inicial
        //this.turnoInicial = new TurnoInicial(catan, tableroModelo, jugadorActual, dados);
        System.out.println(jugadores.getFirst().getNombre());
        System.out.println(jugadores.getLast().getNombre());
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
                    modoActual = ModoJuego.CONSTRUIR_CARRETERA;
                } catch (Exception ex) {
                    System.err.println("Error construir poblado: " + ex.getMessage());
                }
            }


        } catch (Exception ex) {
            System.err.println("Error en vértice: " + ex.getMessage());
        }

        //modoActual = ModoJuego.SELECCIONAR_NADA;
    }
    @Override
    protected  void manejarClickArista(Arista a, Line ui) {
        if (modoActual == ModoJuego.CONSTRUIR_CARRETERA) {
            try {
                int origen = a.getPar().getDestino().getNumeroDeVertice();
                int destino = a.getDestino().getNumeroDeVertice();
                turnoActual.construirCarretera(new int[]{origen, destino});
                ui.setStroke(Color.BLUE);
                catan.terminarTurno();
            } catch (Exception ex) {
                System.err.println("Error construir carretera: " + ex.getMessage());
            }
        }
/*
        try {
            Carretera c = new Carretera(jugadorActual);
            a.ubicarCarretera(c, a.getNumeroDeVertices());

            ui.setStroke(Color.BLUE);
            ui.setOpacity(1);

        } catch (Exception ex) {
            System.err.println("Error en arista: " + ex.getMessage());
        }*/

        modoActual = ModoJuego.SELECCIONAR_NADA;
    }

    @FXML
    public void construirPoblado() {
        modoActual = ModoJuego.CONSTRUIR_POBLADO;
    }

    @FXML
    public void construirCarretera() {
        modoActual = ModoJuego.CONSTRUIR_CARRETERA;
    }

    @FXML
    public void terminarTurnoInicial(ActionEvent event) {
        System.out.println("Turno inicial terminado.");
        this.catan.terminarTurno();
        this.cambiarAJuegoController(event);
        setValores();

        // TODO: aca llamás al administrador de jugadores y verificas si pasas al turno general
    }

    public void cambiarAJuegoController(ActionEvent event){
        if (turnoActual instanceof TurnoGeneral){
            cambiarEscena(event, "/fxml/Juego.fxml", catan);
        }
    }

    @FXML
    public void salir() {
        System.exit(0);
    }

    private void cambiarEscena(ActionEvent event, String fxml, Catan catan)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene nueva = new Scene(loader.load());

            JuegoController controller = loader.getController();
            controller.init(catan);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(nueva);
            stage.show();

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}