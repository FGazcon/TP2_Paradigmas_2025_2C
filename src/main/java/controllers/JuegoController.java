package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.TurnoGeneral;
import model.Dados.Dados;
import model.Jugador.Jugador;
import model.Recurso.*;
import model.Tablero.Arista.Arista;
import model.Tablero.Hexagono;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.Poblado;
import model.Tablero.Vertice.Vertice;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class JuegoController extends BaseTableroController implements Initializable {

    @FXML public Button btnCiudad;
    @FXML public Button btnPoblado;
    @FXML public Button btnCarretera;
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
    //@FXML private Button colocarPoblado;
    @FXML private Button btnDados;

    private Banco banco = new Banco();
    private Catan catan;
    private Dados dados = new Dados();
    private Map<Label, Recurso> lblRecursos;
    private TurnoGeneral turnoActual;
    private Hexagono hexagonoPrevioLadron = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Llamar a una instancia de java.Tablero que contenga la lista de Hexagonos
        this.lblRecursos = crearMapRecursos();
    }
    public void init(Catan catan){
        this.catan = catan;
        this.jugadorActual = this.catan.getTurno().getJugadorActual();
        this.tableroModelo = this.catan.getTablero();
        this.jugadores = catan.getJugadores();
       // this.tableroPane.getChildren().add(tablero);
        bloqueador();

        this.turnoActual = catan.getTurno().getTurnoGeneral();
        System.out.println(turnoActual);
        //modoActual = ModoJuego.CONSTRUIR_POBLADO;
        setValores();

    //hay q cargar  el mapa ya pintado
        asignarCoordenadas(tableroModelo.getHexagonos());
        asignarCoordenadasVertices(tableroModelo);
        crearTablero(tableroModelo);
        recuperarTablero();
    }
    public void recuperarTablero(){
        List<Hexagono> hexagonos = catan.getTablero().getHexagonos();
        for (Hexagono h : hexagonos){
            List<Vertice> vertices = List.of(h.getVertices());
            for (Vertice v : vertices){
                Estructura estructura =v.getEstructura();
                if( estructura instanceof Poblado){
                    Jugador jugador = estructura.getJugador();
                    dibujarCirculo(v,jugador);
                    List<Arista> aristas = v.getAristas();
                    for(Arista a : aristas){
                        if(a.getCarretera() != null) {
                            Line arista = encontrarAristaUI(a);
                            if (arista != null) {
                                colorear(arista, jugador);
                            }
                        }
                    }
                }
            }
        }
    }
    private void dibujarCirculo(Vertice v,Jugador jugador){
        double x = v.getCoordenadaX();
        double y = v.getCoordenadaY();
        this.tableroPane.getChildren().stream()
                .filter(node -> node instanceof Circle)
                .map(node -> (Circle) node)
                .filter(circle -> circle.getCenterX() == x && circle.getCenterY() == y)
                .findFirst().ifPresent(c -> colorear(c, jugador));
    }
    private Line encontrarAristaUI( Arista a) {

        double ox = a.getPar().getDestino().getCoordenadaX();
        double oy = a.getPar().getDestino().getCoordenadaY();
        double dx = a.getDestino().getCoordenadaX();
        double dy = a.getDestino().getCoordenadaY();

        return this.tableroPane.getChildren().stream()
                .filter(node -> node instanceof Line)
                .map(node -> (Line) node)
                .filter(line ->
                        // Mismo sentido
                        (line.getStartX() == ox &&
                                line.getStartY() == oy &&
                                line.getEndX() == dx &&
                                line.getEndY() == dy)
                                ||
                                // Sentido invertido
                                (line.getStartX() == dx &&
                                        line.getStartY() == dy &&
                                        line.getEndX() == ox &&
                                        line.getEndY() == oy)
                )
                .findFirst()
                .orElse(null);
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

        //pintarHexagonoLadron(hex);
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
    public void bloqueador(){
        this.cruz = new Label("✖");
        cruz.setStyle("-fx-font-size: 40px; -fx-text-fill: red;");
        cruz.setMouseTransparent(true);
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

    public void pintarHexagonoLadron(Hexagono hex, Map<Hexagono, StackPane> uiH ) {
        // 1. Desmarcar hexágono anterior

        if (hexagonoPrevioLadron != null) {
            StackPane uiPrevio = uiH.get(hexagonoPrevioLadron);
            if (uiPrevio != null) {
                uiPrevio.getChildren().remove(cruz);
            }
        }

        // 2. Marcar hexágono actual
        StackPane uiActual = uiH.get(hex);
        if (uiActual != null) {

            uiActual.getChildren().add(cruz);
        }

        // 3. Guardar referencia para la próxima
        hexagonoPrevioLadron = hex;
    }
    @Override
    protected void manejarClickHexagono(Hexagono h,Map<Hexagono, StackPane> uiH) {
        System.out.println("cambiar ladron");
        if (modoActual == ModoJuego.MODO_MOVER_LADRON) {
            moverLadronAHexagono(h,uiH);
        }
    }

    private void moverLadronAHexagono(Hexagono nuevoHex,Map<Hexagono, StackPane> uiH) {
        // 1. Mover ladrón en el modelo
        turnoActual.moverLadron(nuevoHex);
        actualizarRecursos();

        // 2. Pintar en la UI
        pintarHexagonoLadron(nuevoHex,uiH);


        // 3. Salir del modo
        modoActual = ModoJuego.SELECCIONAR_NADA;

        // 4. (Opcional) Mostrar mensaje
        lblTurno.setText("Ladrón movido. Continuá tu turno.");
    }




}

//hay que configurar para no pintar poblados arriba de poblados, para no pintar carreteras arriba de carreteras o donde no debe
// y para no poner el ladron en el hexagono donde ya estaba (hacer para que deba elegir hasta que se elija un lugar valido)
