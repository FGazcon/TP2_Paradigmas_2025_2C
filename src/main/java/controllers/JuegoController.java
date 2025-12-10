package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
import model.Desarrollo.CartasDesarrollo.*;
import model.Jugador.Jugador;
import model.Recurso.*;
import model.Tablero.Arista.Arista;
import model.Tablero.Hexagono;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.Poblado;
import model.Tablero.Vertice.Vertice;

import java.net.URL;
import java.util.*;

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
    private Jugador jugadorATradear;

    protected ActivacionDesarrollo activacionPendiente;

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
                    if(v.getEstructura() instanceof Ciudad && v.getEstructura().getJugador().equals(this.jugadorActual)) {
                        ui.setUserData("Ciudad");
                        colorarCiudad(ui);
                        this.modoActual = ModoJuego.SELECCIONAR_NADA;
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
        popup.setAlwaysOnTop(true);
        popup.show();
    }

    private void mostrarElegirJugador() {
        Stage popup = new Stage();
        popup.setTitle("Comercio de Recursos");

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        for(Jugador jugador : this.jugadores){

            Button botonJugador = new Button(jugador.getNombre());
            botonJugador.setPrefWidth(180);
            botonJugador.setDisable(jugador.equals(this.jugadorActual));
            botonJugador.setOnAction(e -> jugadorElegido(jugador,popup));
            layout.getChildren().add(botonJugador);
        }


        popup.setScene(new Scene(layout, 250, 160));
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.show();
    }
    private void mostrarElegirComercio() {
        Stage popup = new Stage();
        popup.setTitle("Elegir Comercio");

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        Button boton1 = new Button("Comerciar con banco");
        Button boton2 = new Button("Comerciar con jugador");

        boton1.setOnAction(e -> comerciarConBanco(popup));
        boton2.setOnAction(e -> comerciarConJugador(popup));

        layout.getChildren().addAll(boton1,boton2);



        popup.setScene(new Scene(layout, 250, 160));
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.show();
    }
    private void comerciarConBanco(Stage popup){
        System.out.println("Sss");
        popup.close();
    }
    private void jugadorElegido(Jugador jugador,Stage popup){
        System.out.println(jugador.getNombre());
        this.jugadorATradear = jugador;
        popup.close();
    }

    private void manejarOfrecer() {
        this.modoActual = ModoJuego.RECURSO_OFRECER;
    }

    private void manejarNecesitar() {
        this.modoActual = ModoJuego.RECURSO_NECESITADO;
    }

    private void manejarEnviarTrade(Stage popup){
         turnoGeneral.comerciar(this.jugadorATradear,this.recursosOfrecer,this.recursosElegir);
         this.recursosElegir.clear();
         this.recursosOfrecer.clear();
         actualizarRecursos();
         popup.close();
         btnComercio.setDisable(false);
    }

    @FXML
    private void comprarCartaDesarrollo() {

        if (!CartaDesarrollo.jugadorMePuedePagar(jugadorActual)) {
            mostrarErrorPopup("No tenés recursos suficientes para comprar una carta.");
            return;
        }

        turnoGeneral.comprarDesarrollo();   // Esto ya agrega la carta a jugadorActual
        actualizarRecursos();

        mostrarInfoPopup("¡Compraste una carta de desarrollo!");
    }


    /*
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
    */

    @FXML
    private void mostrarVentanaUsarDesarrollo() {
        Stage popup = new Stage();
        popup.setTitle("Usar Desarrollo");

        ListView<String> lista = new ListView<>();
        lista.setPrefSize(220, 180);

        // 1) Cartas que SÍ se pueden usar este turno
        List<CartaDesarrollo> activables = jugadorActual.getCartasDesarrolloSinActivar();

        // 2) Cartas recién compradas (NO se pueden usar)
        List<CartaDesarrollo> recienCompradas = jugadorActual.getCartasDesarrolloRecienCompradas();

        // === Mostrar primero las usables ===
        for (CartaDesarrollo c : activables) {
            lista.getItems().add(c.getClass().getSimpleName() + " (usable)");
        }

        // === Mostrar después las NO usables ===
        for (CartaDesarrollo c : recienCompradas) {
            lista.getItems().add(c.getClass().getSimpleName() + " (no usable este turno)");
        }

        Button btnUsar = new Button("Usar Carta");
        btnUsar.setPrefWidth(180);

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setPrefWidth(180);

        btnUsar.setOnAction(e -> {
            int index = lista.getSelectionModel().getSelectedIndex();
            if (index < 0) {
                mostrarErrorPopup("Seleccioná una carta.");
                return;
            }

            if (index >= activables.size()) {
                mostrarErrorPopup("No podés usar una carta recién comprada.");
                return;
            }

            // Obtener la carta real activable
            CartaDesarrollo carta = activables.get(index);

            // Preparar activación
            ActivacionDesarrollo act = jugadorActual.getActivacionParaCartaEnPosicion(index);

            popup.close();

            // Lógica según el tipo de carta
            if (carta instanceof Caballero) {
                mostrarPopupCaballero((Caballero) carta, act);
            }
            else if (carta instanceof Monopolio) {
                mostrarPopupMonopolio((Monopolio) carta, act);
            }
            else if (carta instanceof Descubrimiento) {
                mostrarPopupDescubrimiento((Descubrimiento) carta, act);
            }
            else if (carta instanceof ConstruccionDeCarreteras) {
                mostrarPopupCarreteras((ConstruccionDeCarreteras) carta, act);
            }
            else if (carta instanceof PuntoDeVictoria) {
                mostrarPopupPuntoDeVictoria((PuntoDeVictoria) carta, act);
            }
        });

//        popup.initModality(Modality.WINDOW_MODAL); //este para bloquear las otras ventanas
//        popup.setAlwaysOnTop(true); //este para que se mantenga siempre arriba

        btnCancelar.setOnAction(e -> popup.close());

        VBox layout = new VBox(15, lista, btnUsar, btnCancelar);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 300, 320));
        popup.show();
    }


    private void mostrarPopupMonopolio(CartaDesarrollo carta, ActivacionDesarrollo act) {

        Stage popup = new Stage();
        popup.setTitle("Monopolio");

        Label label = new Label("Elegí un recurso para monopolizar:");
        label.setStyle("-fx-font-weight: bold;");

        Button b1 = new Button("Madera");
        Button b2 = new Button("Ladrillo");
        Button b3 = new Button("Oveja");
        Button b4 = new Button("Trigo");
        Button b5 = new Button("Piedra");

        List<Button> botones = List.of(b1, b2, b3, b4, b5);
        botones.forEach(b -> b.setPrefWidth(180));

        // Acciones de cada botón
        b1.setOnAction(e -> usarMonopolio("Madera", act, popup, carta));
        b2.setOnAction(e -> usarMonopolio("Ladrillo", act, popup, carta));
        b3.setOnAction(e -> usarMonopolio("Oveja", act, popup, carta));
        b4.setOnAction(e -> usarMonopolio("Trigo", act, popup, carta));
        b5.setOnAction(e -> usarMonopolio("Piedra", act, popup, carta));

        VBox layout = new VBox(12, label, b1, b2, b3, b4, b5);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 260, 300));
        popup.show();
    }

    private void usarMonopolio(String nombreRecurso, ActivacionDesarrollo act, Stage popup, CartaDesarrollo carta) {

        // Obtener el Recurso real desde el jugador
        Recurso recurso;
        switch (nombreRecurso) {
            case "Madera" -> recurso = jugadorActual.getMadera();
            case "Ladrillo" -> recurso = jugadorActual.getLadrillo();
            case "Oveja" -> recurso = jugadorActual.getOveja();
            case "Trigo" -> recurso = jugadorActual.getTrigo();
            case "Piedra" -> recurso = jugadorActual.getPiedra();
            default -> throw new IllegalStateException("Recurso desconocido: " + nombreRecurso);
        }

        // Setear el recurso dentro de la activación anónima
        try {
            var metodo = act.getClass().getDeclaredMethod("setRecurso", Recurso.class);
            metodo.setAccessible(true);
            metodo.invoke(act, recurso);
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarErrorPopup("Error interno al asignar recurso al monopolio.");
            return;
        }

        // Ejecutar la carta
        act.ejecutar(this.jugadorActual, this.tableroModelo, this.jugadores);
        jugadorActual.marcarCartaComoUsada(carta);

        // Actualizar interfaz
        actualizarRecursos();

        popup.close();

        mostrarInfoPopup("Monopolizaste " + nombreRecurso + ".");
    }

    private void mostrarPopupDescubrimiento(CartaDesarrollo carta, ActivacionDesarrollo act) {
        Stage popup = new Stage();
        popup.setTitle("Año de Abundancia");

        Label label = new Label("Elegí DOS recursos:");
        label.setStyle("-fx-font-weight: bold;");

        ListView<String> lista = new ListView<>();
        lista.getItems().addAll("Madera", "Ladrillo", "Oveja", "Trigo", "Piedra");
        lista.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lista.setPrefSize(200, 160);

        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setPrefWidth(160);

        btnAceptar.setOnAction(e -> {
            var seleccion = lista.getSelectionModel().getSelectedItems();

            if (seleccion.size() != 2) {
                mostrarErrorPopup("Debés elegir EXACTAMENTE 2 recursos.");
                return;
            }

            usarDescubrimiento(seleccion.get(0), seleccion.get(1), act, popup, carta);
        });

        VBox layout = new VBox(12, label, lista, btnAceptar);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 280, 300));
        popup.show();
    }

    private void usarDescubrimiento(String r1, String r2, ActivacionDesarrollo act, Stage popup, CartaDesarrollo carta) {

        Recurso recursoA = obtenerRecursoJugadorPorNombre(r1);
        Recurso recursoB = obtenerRecursoJugadorPorNombre(r2);

        // Llamar al setter privado "setRecursos(Recurso, Recurso)" usando reflexión
        try {
            var metodo = act.getClass().getDeclaredMethod("setRecursos", Recurso.class, Recurso.class);
            metodo.setAccessible(true);
            metodo.invoke(act, recursoA, recursoB);
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarErrorPopup("Error interno al asignar recursos.");
            return;
        }

        // Ejecutar la activación
        act.ejecutar(jugadorActual, tableroModelo, jugadores);
        jugadorActual.marcarCartaComoUsada(carta);

        actualizarRecursos();
        popup.close();

        mostrarInfoPopup("Obtuviste 1 " + r1 + " y 1 " + r2 + ".");
    }

    private Recurso obtenerRecursoJugadorPorNombre(String nombre) {
        return switch (nombre) {
            case "Madera" -> jugadorActual.getMadera();
            case "Ladrillo" -> jugadorActual.getLadrillo();
            case "Oveja" -> jugadorActual.getOveja();
            case "Trigo" -> jugadorActual.getTrigo();
            case "Piedra" -> jugadorActual.getPiedra();
            default -> throw new IllegalStateException("Recurso desconocido: " + nombre);
        };
    }

    private void mostrarPopupCaballero(CartaDesarrollo carta, ActivacionDesarrollo act) {

        Stage popup = new Stage();
        popup.setTitle("Carta: Caballero");

        Label lbl = new Label(
                "Vas a usar un Caballero.\n\n" +
                        "Hacé CLICK en el hexágono donde querés mover el ladrón."
        );
        lbl.setWrapText(true);
        lbl.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button btnOk = new Button("Aceptar");
        btnOk.setPrefWidth(140);

        btnOk.setOnAction(e -> {
            activarModoMoverLadron(act);  // <<< Activa el modo especial
            popup.close();
        });

        VBox layout = new VBox(15, lbl, btnOk);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 340, 180));
        popup.show();
    }

    @Override
    protected void manejarClickHexagono(Hexagono h, Map<Hexagono, StackPane> uiH) {

        if (modoActual == ModoJuego.MODO_MOVER_LADRON) {

            // Caso 1: Carta Caballero activa
            if (activacionPendiente != null) {
                try {
                    // Setear hex
                    var metodo = activacionPendiente.getClass()
                            .getDeclaredMethod("setHex", Hexagono.class);
                    metodo.setAccessible(true);
                    metodo.invoke(activacionPendiente, h);

                    // Ejecutar mover ladrón
                    activacionPendiente.ejecutar(jugadorActual, tableroModelo, jugadores);

                    moverLadronAHexagono(h, uiH);

                    // Robar
                   // manejarRoboTrasLadron(h);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                activacionPendiente = null;
                return;
            }

            // Caso 2: Movimiento normal del ladrón (si existe esa acción en tu juego)
            this.turnoGeneral.moverLadron(h);
            moverLadronAHexagono(h, uiH);
            modoActual = ModoJuego.SELECCIONAR_NADA;
            return;
        }

        // Otros modos (poblado / ciudad / carretera / comercio / etc.)
    }

    protected void manejarRoboTrasLadron(Hexagono hex) {

        List<Jugador> adyacentes = jugadoresAdyacentes(hex);

        if (adyacentes.isEmpty()) {
            mostrarInfoPopup("No hay jugadores adyacentes para robar.");
            return;
        }

        if (adyacentes.size() == 1) {
            Jugador victima = adyacentes.getFirst();
            victima.dejarseRobarPorJugador(jugadorActual);
            mostrarInfoPopup("Robaste a " + victima.getNombre());
            return;
        }

        mostrarPopupElegirVictima(adyacentes);
    }

    protected List<Jugador> jugadoresAdyacentes(Hexagono hex) {

        Set<Jugador> encontrados = new HashSet<>();

        for (Vertice v : hex.getVertices()) {
            Estructura est = v.getEstructura();
            if (est != null) {
                encontrados.add(est.getJugador());
            }
        }

        // No puede robarse a sí mismo
        encontrados.remove(jugadorActual);

        return new ArrayList<>(encontrados);
    }

    protected void mostrarPopupElegirVictima(List<Jugador> victimas) {
        Stage popup = new Stage();
        popup.setTitle("Elegí jugador a robar");

        ListView<String> lista = new ListView<>();
        for (Jugador j : victimas) lista.getItems().add(j.getNombre());

        Button robar = new Button("Robar");
        robar.setOnAction(e -> {
            int idx = lista.getSelectionModel().getSelectedIndex();
            if (idx < 0) return;

            Jugador victima = victimas.get(idx);
            victima.dejarseRobarPorJugador(jugadorActual);

            popup.close();
            mostrarInfoPopup("Robaste a " + victima.getNombre());
        });

        VBox root = new VBox(12, lista, robar);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(root, 260, 250));
        popup.show();
    }

    private void activarModoMoverLadron(ActivacionDesarrollo act) {
        this.activacionPendiente = act;
        this.modoActual = ModoJuego.MODO_MOVER_LADRON;
        mostrarInfoPopup("Hacé click en el hexágono donde querés mover el ladrón.");
    }

    private void mostrarPopupPuntoDeVictoria(CartaDesarrollo carta, ActivacionDesarrollo act) {

        Stage popup = new Stage();
        popup.setTitle("Punto de Victoria");

        Label lbl = new Label(
                "¡Esta carta te otorga 1 Punto de Victoria!"
        );
        lbl.setWrapText(true);
        lbl.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button btnOk = new Button("Aceptar");
        btnOk.setPrefWidth(120);

        btnOk.setOnAction(e -> {
            act.ejecutar(jugadorActual, tableroModelo, jugadores);
            jugadorActual.marcarCartaComoUsada(carta);
            actualizarRecursos(); // si mostrás puntaje acá, sino sacalo
            popup.close();

            mostrarInfoPopup("Ahora tenés " + jugadorActual.getPuntos() + " puntos.");
        });

        VBox layout = new VBox(15, lbl, btnOk);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 320, 160));
        popup.show();
    }

    private void mostrarPopupCarreteras(CartaDesarrollo carta, ActivacionDesarrollo act) {

        Stage popup = new Stage();
        popup.setTitle("Construcción de Carreteras");

        Label lbl = new Label(
                "Esta carta te permite construir 2 carreteras gratis.\n\n" +
                        "Elegí dos aristas en el tablero donde quieras ubicarlas."
        );
        lbl.setWrapText(true);
        lbl.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        Button btnOk = new Button("Aceptar");
        btnOk.setPrefWidth(140);

        btnOk.setOnAction(e -> {
            activarModoCarreteras(act);
            popup.close();
        });

        VBox layout = new VBox(15, lbl, btnOk);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #E6D0B4; -fx-border-color: black;");

        popup.setScene(new Scene(layout, 380, 180));
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
    public void elegirComcercio(){
        mostrarElegirComercio();
    }
    public void comerciarConJugador(Stage popup){
        popup.close();
        mostrarElegirJugador();
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

    private void moverLadronAHexagono(Hexagono nuevoHex,Map<Hexagono, StackPane> uiH) {
        // 1. Mover ladrón en el modelo
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

// pasar el click arista a juego e inicial controller devuelta para poder actualizar los recursos
// probar usar carta de desarrollo teniendo 1 de un turno anterior y habiendo comprado otro (2 caballeros ejemplos)
// solo se deberia poder usar 1


//las cosas funcionan para el jugador 1 pero no para el 2