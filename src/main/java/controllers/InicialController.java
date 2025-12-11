package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.TurnoGeneral;
import model.Dados.Dados;
import model.Tablero.Hexagono;
import model.Tablero.Vertice.Estructura.Poblado;
import model.Tablero.Vertice.Vertice;
import view.InicialObserver;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class InicialController extends BaseTableroController implements Initializable {

    @FXML private Label lblJugadorActual;
    @FXML private Label lblTurno;

    @FXML private Pane tableroPane;

    @FXML private BorderPane rootInicial;

    //@FXML private Button btnCarretera;
    //@FXML private Button btnPoblado;

    private Banco banco = new Banco();
    private Catan catan;
    private Dados dados = new Dados();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Crear tablero
        // List<Hexagono> hexs = Factory_MapaBasico.crearHexagonosBasico();

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
        crearTablero(tableroModelo);

        new InicialObserver(catan, this);
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
                     if(v.getEstructura() instanceof Poblado && v.getEstructura().getJugador().equals(this.jugadorActual)) {
                         ui.setUserData("Poblado");
                         colorear(ui, this.jugadorActual);
                         modoActual = ModoJuego.CONSTRUIR_CARRETERA;
                     }
                } catch (Exception ex) {
                    System.err.println("Error construir poblado: " + ex.getMessage());
                }
            }


        } catch (Exception ex) {
            System.err.println("Error en vértice: " + ex.getMessage());
        }

        //modoActual = ModoJuego.SELECCIONAR_NADA;
    }
    // hay que hacer un chequeo de si deja colocar arista o vertice, porque pintamos aunque no se pueda y se saltea de una.


    @Override
    protected void manejarClickHexagono(Hexagono h, Map<Hexagono, StackPane> uiH) {
    }




    @FXML
    public void terminarTurnoInicial(ActionEvent event) {
        System.out.println("Turno inicial terminado.");
        System.out.println(this.turnoActual);
        this.catan.terminarTurno();
        this.turnoActual= catan.getTurno();
        this.cambiarAJuegoController(event);
        setValores();
        modoActual = ModoJuego.CONSTRUIR_POBLADO;

        // TODO: aca llamás al administrador de jugadores y verificas si pasas al turno general
    }

    public void cambiarAJuegoController(ActionEvent event){
        if (turnoActual instanceof TurnoGeneral){
            cambiarEscena(event, "/fxml/juego.fxml", catan);
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

    // =====================================================================
    //   LLAMADOS DESDE EL OBSERVER PARA ACTUALIZAR LA UI
    // =====================================================================

    public void uiPobladoInicialConstruido() {
        // Pasar de modo poblado → carretera
        this.modoActual = ModoJuego.CONSTRUIR_CARRETERA;
    }

    public void uiCarreteraInicialConstruida() {
        // Terminó la parte de construcción → habilitar nuevo turno
        this.modoActual = ModoJuego.SELECCIONAR_NADA;
    }

    public void uiCambioTurnoInicial() {
        this.jugadorActual = catan.getTurno().getJugadorActual();
        this.lblJugadorActual.setText(jugadorActual.getNombre());
        this.lblTurno.setText("Turno Inicial de " + jugadorActual.getNombre());
        this.modoActual = ModoJuego.CONSTRUIR_POBLADO;
    }

    public void uiFinTurnoInicial() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/juego.fxml"));
            Scene nueva = new Scene(loader.load());

            JuegoController controller = loader.getController();
            controller.init(catan);

            Stage stage = (Stage) rootInicial.getScene().getWindow();
            stage.setScene(nueva);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}