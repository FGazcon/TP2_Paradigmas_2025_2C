package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Banco.Banco;
import model.Catan.Catan;
import model.Catan.TurnoGeneral;
import model.Dados.Dados;
import model.Desarrollo.CartasDesarrollo.CartaDesarrollo;
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
    @FXML public Button btnComprarDesarrollo;

    @FXML private Label lblInvMadera;
    @FXML private Label lblInvLadrillo;
    @FXML private Label lblInvOveja;
    @FXML private Label lblInvTrigo;
    @FXML private Label lblInvPiedra;

    @FXML private Button btnComercio;
    //@FXML private Button colocarPoblado;
    @FXML private Button btnDados;

    private final Banco banco = new Banco();
    private Catan catan;
    private final Dados dados = new Dados();
    private Map<Label, Recurso> lblRecursos;
    private TurnoGeneral turnoGeneral;
    private Hexagono hexagonoPrevioLadron = null;
    private List<Recurso> recursosOfrecer;
    private List<Recurso> recursosElegir;

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
        bloqueador();

        this.turnoActual = catan.getTurno().getTurnoGeneral();
        this.turnoGeneral= catan.getTurno().getTurnoGeneral();
        System.out.println(turnoActual);
        setValores();

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
                        (line.getStartX() == ox &&
                                line.getStartY() == oy &&
                                line.getEndX() == dx &&
                                line.getEndY() == dy)
                                ||
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
                    if(v.getEstructura() instanceof Poblado && v.getEstructura().getJugador().equals(this.jugadorActual)) {
                        ui.setUserData("Poblado");
                        colorear(ui, this.jugadorActual);
                        modoActual = ModoJuego.SELECCIONAR_NADA;
                        actualizarRecursos();
                    }
                } catch (Exception ex) {
                    System.err.println("Error construir poblado: " + ex.getMessage());
                }
            }

            if (modoActual == ModoJuego.CONSTRUIR_CIUDAD) {
                try {
                    turnoActual.construirCiudad(v.getNumeroDeVertice());
                    if(v.getEstructura().getJugador().equals(this.jugadorActual) && v.getEstructura().getJugador().equals(this.jugadorActual)) {
                        ui.setUserData("Ciudad");
                        colorarCiudad(ui);
                        this.modoActual = ModoJuego.CONSTRUIR_CARRETERA;
                        actualizarRecursos();

                    }
                } catch (Exception ex) {
                    System.err.println("Error construir ciudad: " + ex.getMessage());
                }
            }


        } catch (Exception ex) {
            System.err.println("Error en vértice: " + ex.getMessage());
        }

    }
//    @Override
//    protected void manejarClickArista(Arista a, Line ui) {
//        if (modoActual == ModoJuego.CONSTRUIR_CARRETERA) {
//            try {
//                int origen = a.getPar().getDestino().getNumeroDeVertice();
//                int destino = a.getDestino().getNumeroDeVertice();
//                turnoActual.construirCarretera(new int[]{origen, destino});
//                if(a.getCarretera() != null){
//                    ui.setUserData("Carretera");
//                    colorear(ui,this.jugadorActual);
//                }
//            } catch (Exception ex) {
//                System.err.println("Error construir carretera: " + ex.getMessage());
//            }
//        }
//
//
//
//        modoActual = ModoJuego.SELECCIONAR_NADA;
//    }

    // recursos intercambio

    private void mostrarVentanaComercio() {
        Stage popup = new Stage();
        popup.setTitle("Comercio de Recursos");

        // ☆ Botones
        Button btnOfrecer = new Button("Recursos a ofrecer");
        Button btnNecesitar = new Button("Recursos a necesitar");
        Button btnEnviarTrade = new Button("Enviar Trade");


        //debe agregar cartas a la lista de recursos que ofrece
        btnOfrecer.setPrefWidth(180);
        //debe agregar cartas a la lista de recursos que necesita
        btnNecesitar.setPrefWidth(180);
        btnEnviarTrade.setPrefWidth(180);

        btnOfrecer.setOnAction(e -> manejarOfrecer());
        btnNecesitar.setOnAction(e -> manejarNecesitar());
        btnEnviarTrade.setOnAction(e -> manejarEnviarTrade(popup));

        // ☆ Layout simple
        VBox layout = new VBox(15, btnOfrecer, btnNecesitar, btnEnviarTrade);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 250, 160));
        popup.initModality(Modality.NONE);
        popup.show();
    }

    private void manejarOfrecer() {
        this.modoActual = ModoJuego.RECURSO_OFRECER;
    }

    private void manejarNecesitar() {
        this.modoActual = ModoJuego.RECURSO_NECESITADO;
    }

    private void manejarEnviarTrade(Stage popup){
         turnoGeneral.comerciar(jugadores.getLast(),this.recursosOfrecer,this.recursosElegir);
         this.recursosElegir.clear();
         this.recursosOfrecer.clear();
         actualizarRecursos();
         popup.close();
         btnComercio.setDisable(false);
    }

    @FXML
    private void mostrarVentanaComprarDesarrollo() {
        Stage popup = new Stage();
        popup.setTitle("Comprar Desarrollo");

        Button btnComprarDesarrollo = new Button("Comprar Carta");
        btnComprarDesarrollo.setPrefWidth(180);

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setPrefWidth(180);

        btnComprarDesarrollo.setOnAction(e -> {
            popup.close();
            mostrarPopupElegirTipoCarta();
        });

        btnCancelar.setOnAction(e -> popup.close());

        VBox layout = new VBox(15, btnComprarDesarrollo, btnCancelar);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 250, 160));
        popup.initModality(Modality.NONE);
        popup.show();
    }

    private void mostrarPopupElegirTipoCarta() {
        Stage popup = new Stage();
        popup.setTitle("Elegí qué carta comprar");

        Button btnCaballero = new Button("Caballero");
        Button btnMonopolio = new Button("Monopolio");
        Button btnDescubrimiento = new Button("Descubrimiento");
        Button btnCarreteras = new Button("Carreteras");
        Button btnPuntoVictoria = new Button("Punto de Victoria");

        List<Button> botones = List.of(
                btnCaballero, btnMonopolio, btnDescubrimiento, btnCarreteras, btnPuntoVictoria
        );

        botones.forEach(b -> b.setPrefWidth(200));

        btnCaballero.setOnAction(e -> intentarComprarTipo("Caballero", popup));
        btnMonopolio.setOnAction(e -> intentarComprarTipo("Monopolio", popup));
        btnDescubrimiento.setOnAction(e -> intentarComprarTipo("Descubrimiento", popup));
        btnCarreteras.setOnAction(e -> intentarComprarTipo("Carreteras", popup));
        btnPuntoVictoria.setOnAction(e -> intentarComprarTipo("Punto de Victoria", popup));

        VBox layout = new VBox(12);
        layout.getChildren().addAll(botones);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 280, 260));
        popup.show();
    }

    private void intentarComprarTipo(String tipo, Stage popup) {
        if (!CartaDesarrollo.jugadorMePuedePagar(jugadorActual)) {
            mostrarErrorPopup("No tenés recursos suficientes para comprar: " + tipo);
            return;
        }

        turnoGeneral.comprarDesarrollo();

        actualizarRecursos();
        popup.close();
    }

    private void mostrarErrorPopup(String mensaje) {
        Stage popup = new Stage();
        popup.setTitle("Error");

        Label lbl = new Label(mensaje);
        lbl.setWrapText(true);

        Button btnOk = new Button("OK");
        btnOk.setOnAction(e -> popup.close());

        VBox layout = new VBox(15, lbl, btnOk);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F8D7DA; -fx-border-color: #721C24;");

        popup.setScene(new Scene(layout, 260, 120));
        popup.show();
    }


    private Map<Label, Recurso> crearMapRecursos() {
        Map<Label, Recurso> map = new HashMap<>();
        map.put(lblInvMadera, new Madera());
        map.put(lblInvPiedra, new Piedra());
        map.put(lblInvOveja, new Oveja());
        map.put(lblInvLadrillo, new Ladrillo());
        map.put(lblInvTrigo, new Trigo());

        for (var entry : map.entrySet()) {
            Label label = entry.getKey();
            Recurso recurso = entry.getValue();
            label.setOnMouseClicked(e -> {
                if (this.modoActual == ModoJuego.RECURSO_OFRECER) {
                    manejarClickRecurso(recurso, this.recursosOfrecer);
                } else if (this.modoActual == ModoJuego.RECURSO_NECESITADO) {
                    manejarClickRecurso(recurso, this.recursosElegir);
                }
                System.out.println("Recursos ofrecer: " + this.recursosOfrecer);
                System.out.println("Recursos necesitar: " + this.recursosElegir);
            });
            //label.setCursor(Cursor.HAND);   // opcional: mano al pasar
        }

        return map;
    }
    //agrega recursos a una lista
    private void manejarClickRecurso(Recurso recurso,List<Recurso> listaRecurso){
        recurso.agregarALaListaSinRestriccion(listaRecurso);
        //System.out.println("Recursos elegidos " + recurso.getCantidad());

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
        //btnDados.setDisable(true);
        this.turnoGeneral.tirarDados(tirada);

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
    //deberia preguntarle a cada jugador siguiente si acepta o no el cambio y por ultimo vuelve al jugador 1
    // para que elija con quien comerciar o algo asi
    @FXML
    public void comerciarConJugador(){
       this.recursosOfrecer = Recurso.crearListaDeRecursos();
       this.recursosElegir = Recurso.crearListaDeRecursos();

        mostrarVentanaComercio();
        btnComercio.setDisable(true);
    }

    //hacer una funcion que al clickear una carta aniada un recurso a la lista

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
        this.turnoGeneral.moverLadron(nuevoHex);
        actualizarRecursos();

        // 2. Pintar en la UI
        pintarHexagonoLadron(nuevoHex,uiH);


        // 3. Salir del modo
        modoActual = ModoJuego.SELECCIONAR_NADA;

        // 4. (Opcional) Mostrar mensaje
        lblTurno.setText("Ladrón movido. Continuá tu turno.");
    }
    //diferenciacion entre poblado y ciudad, abierto a cambios.
    private void colorarCiudad(Circle verticeCiudad){
        verticeCiudad.setRadius(15);
        verticeCiudad.setOpacity(0.7);
        verticeCiudad.setStroke(Color.BLACK);
        verticeCiudad.setStrokeWidth(2);

    }



}

//hay que configurar para no pintar poblados arriba de poblados, para no pintar carreteras arriba de carreteras o donde no debe
// y para no poner el ladron en el hexagono donde ya estaba (hacer para que deba elegir hasta que se elija un lugar valido)

//update
// me deja cambiar el color de una carretera ya construida pq se cambia el color si la arista contiene una instancia de carretera
// probablemente seria mucho mas facil usar una excepcion en el metodo llamado y seguro sea lo correcto.
