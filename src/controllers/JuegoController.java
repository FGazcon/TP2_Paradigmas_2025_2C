package controllers;

import javafx.scene.control.Button;
import model.Catan.Turno;
import model.Catan.TurnoPersonal;
import model.Recurso.*;
import model.Jugador.Jugador;
import model.Dados.Dados;
import model.Catan.Catan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import model.Tablero.Factory.Factory_MapaBasico;
import model.Tablero.Hexagono;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.Poblado;
import model.Tablero.Vertice.Vertice;
import model.Terreno.Desierto;
import model.Terreno.Productor.*;
import model.Terreno.Terreno;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class JuegoController implements Initializable {

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

    @FXML private Button btnDados;

    // Variables de Control y Modelo
    private Tablero tableroModelo; // Necesitas una clase Tablero en tu modelo
    private Jugador jugadorActual; // Simulación del jugador de turno
    private Dados dados = new Dados();
    private Catan catan = new Catan();
    private TurnoPersonal turnoActual;
    private ModoJuego modoActual = ModoJuego.SELECCIONAR_NADA;
    private Map<Label, String> lblRecursos;

    // Constantes
    private final double RADIO = 60.0;
    private final double DIST_X = RADIO * Math.sqrt(3.0);
    private final double DIST_Y = RADIO * 1.5;
    private final double X_INICIAL = 400.0 - (2.5 * DIST_X);
    private final double Y_INICIAL = 300.0 - (2 * DIST_Y);

    private enum ModoJuego { SELECCIONAR_NADA, CONSTRUIR_POBLADO, CONSTRUIR_CARRETERA, CONSTRUIR_CIUDAD }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Llamar a una instancia de Tablero que contenga la lista de Hexagonos
        List<Hexagono> hexs = Factory_MapaBasico.crearHexagonosBasico();
        this.lblRecursos = crearMapRecursos() ;

        tableroModelo = catan.getTablero();

        jugadorActual = new Jugador("Jugador");

        turnoActual = new TurnoPersonal(catan, tableroModelo, jugadorActual, dados);

        asignarCoordenadas(tableroModelo.getHexagonos());

        asignarCoordenadasVertices(tableroModelo);

        dibujarTablero(tableroModelo);
    }

    private void asignarCoordenadasVertices(Tablero tableroModelo) {

        // Código para asignar Vertices

        for (Hexagono hex : tableroModelo.getHexagonos()) {
            double Cx = hex.getCoordenadaX();
            double Cy = hex.getCoordenadaY();

            Vertice[] verticesDelHex = hex.getVertices();

            for (int i = 0; i < 6; i++) {
                Vertice verticeActual = verticesDelHex[i];

                double anguloRadianes = Math.PI / 2 + i * Math.PI / 3;

                double Vx = Cx + RADIO * Math.cos(anguloRadianes);
                double Vy = Cy - RADIO * Math.sin(anguloRadianes);

                if (!verticeActual.tieneCoordenadasAsignadas()) {
                    verticeActual.setCoordenadas(Vx, Vy);
                }
            }
        }
    }

    private void asignarCoordenadas(List<Hexagono> hexagonos) {
        int[] hexagonossPorFila = {3, 4, 5, 4, 3};
        int hexIndex = 0;

        for (int fila = 0; fila < hexagonossPorFila.length; fila++) {
            int numHexs = hexagonossPorFila[fila];
            double offsetFila = (5 - numHexs) * DIST_X / 2.0;

            for (int col = 0; col < numHexs; col++) {
                if (hexIndex >= hexagonos.size()) break;

                double x = X_INICIAL + offsetFila + (col * DIST_X);
                double y = Y_INICIAL + (fila * DIST_Y);

                hexagonos.get(hexIndex).setCoordenadas(x, y);
                hexIndex++;
            }
        }
    }
    // Declaración del mapa
    public Map<Label, String> crearMapRecursos(){
        Map<Label, String> lblRecursos = new HashMap<>() ;

        // Poblar el mapa: cada Label se asocia con el nombre del recurso
        lblRecursos.put(this.lblInvMadera,"Madera");
        lblRecursos.put(this.lblInvPiedra,"Piedra");
        lblRecursos.put(this.lblInvOveja,"Oveja");
        lblRecursos.put(this.lblInvLadrillo,"Ladrillo");
        lblRecursos.put(this.lblInvTrigo,"Trigo");

        return lblRecursos;
    }

    public void actualizarRecursos(Jugador jugador) {
        for (Map.Entry<Label, String> entry : this.lblRecursos.entrySet()) {
            int cant = jugador.getCantidadDeRecursoEspecifico(entry.getValue());
            entry.getKey().setText(String.valueOf(cant));
        }
    }

    private void dibujarTablero(Tablero modelo) {
        for (Hexagono hex : modelo.getHexagonos()) {
            tableroPane.getChildren().add(crearTile(hex));
        }

        // 2. Dibujar Aristas (carreteras) - DEBES OBTENER LAS ARISTAS ÚNICAS
        // dibujarAristas(modelo.getAristasUnicas());

        // 3. Dibujar Vértices (poblados) - DEBEN IR ÚLTIMOS PARA ESTAR ENCIMA
        // Aca se asume que hay un getter para los 54 vértices únicos del tablero:
        // dibujarVertices(modelo.getVerticesUnicos());
    }

    private StackPane crearTile(Hexagono hex) {
        Color colorHex = obtenerColor(hex.getTerreno());

        Polygon hexagon = new Polygon();
        Double[] points = new Double[12];

        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 2 + i * Math.PI / 3;
            points[i * 2] = hex.getCoordenadaX() + RADIO * Math.cos(angle);
            points[i * 2 + 1] = hex.getCoordenadaY() - RADIO * Math.sin(angle);
        }
        hexagon.getPoints().addAll(points);
        hexagon.setFill(colorHex);
        hexagon.setStroke(Color.BLACK);

        Label numeroLabel = new Label(String.valueOf(hex.getNumero()));
        numeroLabel.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; -fx-background-color: #EEE; -fx-padding: 5; -fx-border-radius: 50; -fx-background-radius: 50;");

        StackPane tile = new StackPane(hexagon);
        if (hex.getNumero() != 7) {
            tile.getChildren().add(numeroLabel);
        }

        tile.setLayoutX(hex.getCoordenadaX());
        tile.setLayoutY(hex.getCoordenadaY());

        hexagon.setOnMouseClicked(e -> System.out.println("Clic en hex " + hex.getNumero()));

        return tile;
    }

    private Color obtenerColor(Terreno terreno) {
        return switch (terreno) {

            // MADERA
            case Bosque bosque -> Color.rgb(107, 142, 35);

            // LADRILLO
            case Colina colina -> Color.rgb(178, 34, 34);

            // OVEJA
            case Pastizal pastizal -> Color.rgb(240, 248, 255);

            // TRIGO
            case Campo campo -> Color.rgb(218, 165, 32);

            // PIEDRA
            case Montaña montaña -> Color.rgb(128, 128, 128);

            // DESIERTO
            case Desierto desierto -> Color.rgb(250, 214, 165);

            // DEFAULT
            default -> Color.rgb(200, 200, 200);
        };
    }

    @FXML
    public void construirPoblado(ActionEvent e) {
        modoActual = ModoJuego.CONSTRUIR_POBLADO;
        System.out.println("Modo: CONSTRUIR POBLADO. Click en un vertice.");
    }

    @FXML
    public void construirCiudad(ActionEvent e) {
        modoActual = ModoJuego.CONSTRUIR_CIUDAD;
        System.out.println("Modo: CONSTRUIR CIUDAD. Click en el poblado a mejorar.");
    }

    @FXML
    public void construirCarretera(ActionEvent e) {
        modoActual = ModoJuego.CONSTRUIR_CARRETERA;
        System.out.println("Modo: CONSTRUIR CARRETERA. Click en una arista.");
    }

    private void dibujarVertices(List<Vertice> verticesUnicos) {
        for (Vertice v : verticesUnicos) {
            Circle verticeUI = crearVerticeUI(v.getCoordenadaX(), v.getCoordenadaY(), v);
            tableroPane.getChildren().add(verticeUI);
        }
    }

    private Circle crearVerticeUI(double x, double y, Vertice modeloVertice) {
        final double R_VERTICE = 12;
        Circle vertice = new Circle(R_VERTICE);
        vertice.setCenterX(x);
        vertice.setCenterY(y);
        vertice.setFill(Color.TRANSPARENT);
        vertice.setStroke(Color.GRAY);
        vertice.setStrokeWidth(1);

        vertice.setOnMouseClicked(e -> manejarClickVertice(modeloVertice, vertice));

        return vertice;
    }

    private void manejarClickVertice(Vertice modeloVertice, Circle verticeUI) {
        if (modoActual == ModoJuego.CONSTRUIR_POBLADO) {
            try {
                Estructura nuevaEstructura = new Poblado(jugadorActual);

                modeloVertice.ubicarEstructura(nuevaEstructura);

                verticeUI.setFill(Color.BLUE);
                modoActual = ModoJuego.SELECCIONAR_NADA;
                System.out.println("Poblado construido en vertice: " + modeloVertice.getNumeroDeVertice());

            } catch (Exception ex) {
                System.err.println("Error de construcción: " + ex.getMessage());
            }

        }

        if (modoActual == ModoJuego.CONSTRUIR_CIUDAD) {
            try {
                Estructura nuevaEstructura = new Ciudad(jugadorActual);

                modeloVertice.ubicarEstructura(nuevaEstructura);

                verticeUI.setFill(Color.BLUE);
                modoActual = ModoJuego.SELECCIONAR_NADA;
                System.out.println("Ciudad construida en vertice: " + modeloVertice.getNumeroDeVertice());

            } catch (Exception ex) {
                System.err.println("Error de construcción: " + ex.getMessage());
            }
        }
    }

    @FXML
    public void tirarDados(ActionEvent e) {

        // if (btnDados.isDisable()) {
        //     System.out.println("Los dados ya fueron tirados este turno.");
        //     return;
        // }

        int tirada = dados.tirarDados();
        turnoActual.tirarDados(tirada);
        lblValorDados.setText("Dados: " + tirada);
        actualizarRecursos(jugadorActual);

        // btnDados.setDisable(true);
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

        if (btnDados != null) {
            btnDados.setDisable(false);
        }
    }

    @FXML
    public void salir(ActionEvent e) {
        System.exit(0);
    }
}