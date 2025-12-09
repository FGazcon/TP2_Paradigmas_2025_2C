package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import model.Catan.Turno;
import model.Jugador.Jugador;
import model.Tablero.Arista.Arista;
import model.Tablero.Hexagono;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Vertice;
import model.Terreno.Desierto;
import model.Terreno.Productor.*;
import model.Terreno.Terreno;

import java.util.*;

public abstract class BaseTableroController implements Initializable {

    @FXML protected Pane tableroPane;

    protected Tablero tableroModelo;
    protected Jugador jugadorActual;
    protected enum ModoJuego { SELECCIONAR_NADA, CONSTRUIR_POBLADO, CONSTRUIR_CARRETERA, CONSTRUIR_CIUDAD, MODO_MOVER_LADRON }
    protected ModoJuego modoActual = ModoJuego.SELECCIONAR_NADA;
    protected final double RADIO = 60.0;
    protected final double DIST_X = RADIO * Math.sqrt(3.0);
    protected final double DIST_Y = RADIO * 1.5;
    // Usa las dimensiones reales del Pane
    protected final double X_INICIAL = 400.0 - (2.5 * DIST_X);
    protected final double Y_INICIAL = 300.0 - (2.0 * DIST_Y);
    protected Label cruz;
    protected List<Jugador> jugadores;


    protected Turno turnoActual; // Puede ser inicial o general
    Map<Hexagono, StackPane> uiHexagonos = new HashMap<>();

    protected void crearTablero(Tablero modelo) {
        for (Hexagono hex : modelo.getHexagonos()) {
            tableroPane.getChildren().add(crearTile(hex));
        }

        aniadirAristas(modelo.getAristasUnicas());
        aniadirVertices(modelo.getVerticesUnicos());
    }


    protected void asignarCoordenadas(List<Hexagono> hexagonos) {
        int[] hexagonosPorFila = {3, 4, 5, 4, 3};
        int hexIndex = 0;

        for (int fila = 0; fila < hexagonosPorFila.length; fila++) {
            int numHexs = hexagonosPorFila[fila];
            double offsetFila = (5 - numHexs) * DIST_X / 2.0;

            for (int col = 0; col < numHexs; col++) {
                if (hexIndex >= hexagonos.size()) break;

                double x = X_INICIAL + offsetFila + (col * DIST_X);
                double y = Y_INICIAL + (fila * DIST_Y);

                hexagonos.get(hexIndex).setCoordenadas(x, y);
                //asignarCoordenadasVertices(hexagonos.get(hexIndex));
                hexIndex++;
            }
        }
    }

    protected void asignarCoordenadasVertices(Hexagono hex) {

        double Cx = hex.getCoordenadaX();
        double Cy = hex.getCoordenadaY();

        Vertice[] vertices = hex.getVertices();

        for (int i = 0; i < 6; i++) {

            double ang = Math.PI / 2 + i * Math.PI / 3;

            double Vx = Cx + RADIO * Math.cos(ang);
            double Vy = Cy - RADIO * Math.sin(ang);

            Vertice v = vertices[i];

            if (!v.tieneCoordenadasAsignadas()) {
                v.setCoordenadas(Vx, Vy);
            }
        }
    }

    protected void asignarCoordenadasVertices(Tablero tableroModelo) {
        Vertice[] vertices;
        for (Hexagono hex : tableroModelo.getHexagonos()) {
            double Cx = hex.getCoordenadaX();
            double Cy = hex.getCoordenadaY();
            vertices = hex.getVertices();

            for (int i = 0; i < 6; i++) {
                Vertice v = vertices[i];

                double ang = (5 * Math.PI / 6) - (i * Math.PI / 3);

                double Vx = Cx + RADIO * Math.cos(ang);
                double Vy = Cy - RADIO * Math.sin(ang);

                if (!v.tieneCoordenadasAsignadas()) {
                    v.setCoordenadas(Vx, Vy);
                }
            }
        }
    }

    protected StackPane crearTile(Hexagono hex) {
        Color colorHex = obtenerColor(hex.getTerreno());

        Polygon polygon = new Polygon();

        //tile.setOnMouseClicked(event -> manejarClickHexagono(hex));
        //tableroPane.getChildren().add(polygon);

        for (int i = 0; i < 6; i++) {
            double ang = Math.PI / 2 + i * Math.PI / 3;

            double x = RADIO * Math.cos(ang);
            double y = -RADIO * Math.sin(ang);

            polygon.getPoints().addAll(x, y);
        }

        polygon.setFill(colorHex);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(2.0);

        StackPane tile = new StackPane(polygon);
        uiHexagonos.put(hex, tile);


        tile.relocate(hex.getCoordenadaX() - (RADIO-2.5), hex.getCoordenadaY() - (RADIO-1));
        tile.setPickOnBounds(false);

        if (!(hex.getTerreno() instanceof Desierto)) {
            Label lbl = new Label(String.valueOf(hex.getNumero()));
            lbl.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");
            tile.getChildren().add(lbl);
        }


        tile.setOnMouseClicked(e -> {
            System.out.println("Click en hexágono " + hex.getNumero());
            //testController();
            manejarClickHexagono(hex,uiHexagonos);
            //System.out.println("Fin manejarClickHexagono");
        });

        return tile;
    }

    protected Color obtenerColor(Terreno t) {
        return switch (t) {
            case Bosque b -> Color.rgb(107, 142, 35);
            case Colina c -> Color.rgb(178, 34, 34);
            case Pastizal p -> Color.rgb(240, 248, 255);
            case Campo ca -> Color.rgb(218, 165, 32);
            case Montaña m -> Color.rgb(128, 128, 128);
            case Desierto d -> Color.rgb(250, 214, 165);
            default -> Color.GRAY;
        };
    }

    protected void aniadirVertices(List<Vertice> vertices) {
        for (Vertice v : vertices)
            tableroPane.getChildren().add(crearVerticeUI(v));
    }

    protected Circle crearVerticeUI(Vertice v) {
        Circle c = new Circle(10);

        c.setCenterX(v.getCoordenadaX());
        c.setCenterY(v.getCoordenadaY());
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        c.setStrokeWidth(2);

        Tooltip.install(c, new Tooltip("Vértice " + v.getNumeroDeVertice()));

        c.setOnMouseClicked(e -> manejarClickVertice(v, c));

        return c;
    }

    protected void aniadirAristas(List<Arista> aristas) {

        Set<Arista> aristasYaDibujadas = new HashSet<>();

        for (Arista a : aristas) {
            if (aristasYaDibujadas.contains(a) || aristasYaDibujadas.contains(a.getPar())) {
                continue;
            }

            tableroPane.getChildren().add(crearAristaUI(a));

            aristasYaDibujadas.add(a);
            aristasYaDibujadas.add(a.getPar());
        }
    }

    protected Line crearAristaUI(Arista a) {

        Vertice origen = a.getPar().getDestino();

        Vertice destino = a.getDestino();

        Line l = new Line(
                origen.getCoordenadaX(),
                origen.getCoordenadaY(),
                destino.getCoordenadaX(),
                destino.getCoordenadaY()
        );

        l.setStroke(Color.BLACK);
        l.setStrokeWidth(6);
        l.setOpacity(0.8);

        l.setOnMouseEntered(e -> l.setOpacity(1));
        l.setOnMouseExited(e -> l.setOpacity(0.6));
        l.setOnMouseClicked(e -> manejarClickArista(a, l));

        return l;
    }



    protected abstract void manejarClickVertice(Vertice v, Circle ui);

    protected abstract void manejarClickArista(Arista a, Line ui);

    public void setTurnoActual(Turno turno) {
        this.turnoActual = turno;
    }

    //

    public void colorear(Shape ui,Jugador jugadorActual){

        if(jugadorActual.equals(jugadores.getFirst())){
            ui.setStroke(Color.BLUE);
            ui.setFill(Color.BLUE);
        }else if(jugadorActual.equals(jugadores.get(1))){
            ui.setFill(Color.TAN);
            ui.setStroke(Color.TAN);
        }else if(jugadorActual.equals(jugadores.get(2))){
            ui.setStroke(Color.PINK);
            ui.setFill(Color.PINK);
        }else if ( !jugadores.getLast().equals(jugadores.get(2))
                && jugadorActual.equals(jugadores.getLast()) ) {
            ui.setStroke(Color.CORNSILK);
            ui.setFill(Color.CORNSILK);
        }

    }

    protected abstract void manejarClickHexagono(Hexagono h,Map<Hexagono, StackPane> uiH);
    protected void testController() {
        System.out.println("Soy: " + this.getClass().getName());
    }

}