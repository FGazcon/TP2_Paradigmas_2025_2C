package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.StackPane;
import model.Catan.Catan;
import model.Jugador.Jugador;
import model.Tablero.Arista.Arista;
import model.Tablero.Arista.Carretera;
import model.Tablero.Hexagono;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.Poblado;
import model.Tablero.Vertice.Vertice;
import model.Terreno.Desierto;
import model.Terreno.Productor.*;
import model.Terreno.Terreno;

import java.util.*;

public abstract class BaseTableroController implements Initializable {

    // ----------------------------
    // CAMPOS COMPARTIDOS
    // ----------------------------
    @FXML protected Pane tableroPane;
    protected Tablero tableroModelo;
    protected Jugador jugadorActual;

    protected enum ModoJuego { SELECCIONAR_NADA, CONSTRUIR_POBLADO, CONSTRUIR_CARRETERA, CONSTRUIR_CIUDAD }
    protected ModoJuego modoActual = ModoJuego.SELECCIONAR_NADA;

    // ----------------------------
    // CONSTANTES DE UI DEL TABLERO
    // ----------------------------
    protected final double RADIO = 60.0;
    protected final double DIST_X = RADIO * Math.sqrt(3.0);
    protected final double DIST_Y = RADIO * 1.5;
    protected final double X_INICIAL = 400.0 - (2.5 * DIST_X);
    protected final double Y_INICIAL = 300.0 - (2 * DIST_Y);

    // ----------------------------
    // DIBUJAR TABLERO COMPLETO
    // ----------------------------
// En controllers.BaseTableroController

    // ----------------------------
// DIBUJAR TABLERO COMPLETO
// ----------------------------
    protected void dibujarTablero(Tablero modelo) {
        for (Hexagono hex : modelo.getHexagonos()) {
            // Ahora se añade el Polygon directamente
            tableroPane.getChildren().add(crearTile(hex));
        }
        // tableroPane.getChildren().add(crearTile(modelo.getHexagonos().get(0)));
        // tableroPane.getChildren().add(crearTile(modelo.getHexagonos().get(1)));
        // tableroPane.getChildren().add(crearTile(modelo.getHexagonos().get(14)));

        //dibujarAristas(modelo.getAristasUnicas());
        // Asegúrate de que esta línea esté DESCOMENTADA para ver los poblados
        dibujarVertices(modelo.getVerticesUnicos());
    }

    // ----------------------------
    // ASIGNAR COORDENADAS A HEXAGONOS
    // ----------------------------

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
/*
    protected void asignarCoordenadasVertices(Hexagono hex) {

        double Cx = hex.getCoordenadaX();   // centro X del hexágono
        double Cy = hex.getCoordenadaY();   // centro Y del hexágono

        Vertice[] vertices = hex.getVertices();

        for (int i = 0; i < 6; i++) {

            // ángulo del vértice i
            double ang = Math.PI / 2 + i * Math.PI / 3;

            // coordenadas del vértice
            double Vx = Cx + RADIO * Math.cos(ang);
            double Vy = Cy - RADIO * Math.sin(ang);

            Vertice v = vertices[i];

            // Solo asigno si ese vértice aún no fue asignado por otro hexágono vecino
            if (!v.tieneCoordenadasAsignadas()) {
            }
            v.setCoordenadas(Vx, Vy);
        }
    }*/

    protected void asignarCoordenadasVertices(Hexagono hex) {

        double Cx = hex.getCoordenadaX();
        double Cy = hex.getCoordenadaY();

        Vertice[] vertices = hex.getVertices();

        for (int i = 0; i < 6; i++) {

            // Para un hexágono flat-topped (Catan):
            // ángulo del vértice i
            double ang = Math.PI / 2 + i * Math.PI / 3;

            // coordenadas del vértice
            double Vx = Cx + RADIO * Math.cos(ang);
            double Vy = Cy - RADIO * Math.sin(ang);

            Vertice v = vertices[i];

            if (!v.tieneCoordenadasAsignadas()) {
                v.setCoordenadas(Vx, Vy);
            }
        }
    }

    // ----------------------------
    // ASIGNAR COORDENADAS A VÉRTICES
    // ----------------------------
    protected void asignarCoordenadasVertices(Tablero tableroModelo) {
        for (Hexagono hex : tableroModelo.getHexagonos()) {
            double Cx = hex.getCoordenadaX();
            double Cy = hex.getCoordenadaY();

            Vertice[] vertices = hex.getVertices();

            for (int i = 0; i < 6; i++) {
                Vertice v = vertices[i];

                double ang = Math.PI / 2 + i * Math.PI / 3;
                double Vx = Cx + RADIO * Math.cos(ang);
                double Vy = Cy - RADIO * Math.sin(ang);

                if (!v.tieneCoordenadasAsignadas()) {
                    v.setCoordenadas(Vx, Vy);
                }
            }
        }
    }

    // ----------------------------
    // DIBUJAR UN HEXÁGONO
    // ----------------------------

// En controllers.BaseTableroController

    // ----------------------------
// DIBUJAR UN HEXÁGONO
// ----------------------------
// El tipo de retorno AHORA es Polygon, no StackPane
    protected Polygon crearTile(Hexagono hex) {
        Color colorHex = obtenerColor(hex.getTerreno());

        Polygon polygon = new Polygon();
        Vertice[] vertices = hex.getVertices();

        // Cargar puntos del polígono usando las coordenadas DEL VÉRTICE (COORDENADAS ABSOLUTAS)
        for (int i = 0; i < 6; i++) {
            Vertice v = vertices[i];

            polygon.getPoints().addAll(
                    v.getCoordenadaX(),
                    v.getCoordenadaY()
            );
        }

        polygon.setFill(colorHex);
        polygon.setStroke(Color.BLACK);

        // ¡IMPORTANTE! Eliminamos el StackPane.
        // return new StackPane(polygon);

        return polygon; // Devolvemos el Polygon directamente.
    }



    // ----------------------------
    // COLOR DE TERRENO
    // ----------------------------
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

    // ----------------------------
    // DIBUJAR VÉRTICES
    // ----------------------------
    protected void dibujarVertices(List<Vertice> vertices) {
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

    // ----------------------------
    // DIBUJAR ARISTAS
    // ----------------------------
// En controllers.BaseTableroController

    // ----------------------------
// DIBUJAR ARISTAS (CORREGIDO)
// ----------------------------
    protected void dibujarAristas(List<Arista> aristas) {

        // Este set almacena las aristas que YA hemos añadido al tablero
        Set<Arista> aristasYaDibujadas = new HashSet<>();

        for (Arista a : aristas) {
            // Si la arista o su par ya fueron dibujadas, la saltamos.
            if (aristasYaDibujadas.contains(a) || aristasYaDibujadas.contains(a.getPar())) {
                continue;
            }

            // 1. Dibujamos la arista
            tableroPane.getChildren().add(crearAristaUI(a));

            // 2. Marcamos esta arista y su par como dibujadas
            aristasYaDibujadas.add(a);
            aristasYaDibujadas.add(a.getPar());
        }
    }



// En controllers.BaseTableroController

    protected Line crearAristaUI(Arista a) {

        // Origen: Es el destino del par (el punto A)
        Vertice origen = a.getPar().getDestino();

        // Destino: Es el destino de la arista actual (el punto B)
        Vertice destino = a.getDestino();

        Line l = new Line(
                origen.getCoordenadaX(),
                origen.getCoordenadaY(),
                destino.getCoordenadaX(),
                destino.getCoordenadaY()
        );

        l.setStroke(Color.GRAY);
        l.setStrokeWidth(6);
        l.setOpacity(0.6);

        l.setOnMouseEntered(e -> l.setOpacity(1));
        l.setOnMouseExited(e -> l.setOpacity(0.6));
        l.setOnMouseClicked(e -> manejarClickArista(a, l));

        return l;
    }

    // ----------------------------
    // CLICK EN VÉRTICE
    // ----------------------------
    protected void manejarClickVertice(Vertice v, Circle ui) {

        try {
            if (modoActual == ModoJuego.CONSTRUIR_POBLADO) {
                v.ubicarEstructura(new Poblado(jugadorActual));
                ui.setFill(Color.BLUE);
            }

            if (modoActual == ModoJuego.CONSTRUIR_CIUDAD) {
                v.ubicarEstructura(new Ciudad(jugadorActual));
                ui.setFill(Color.BLUE);
            }

        } catch (Exception ex) {
            System.err.println("Error en vértice: " + ex.getMessage());
        }

        modoActual = ModoJuego.SELECCIONAR_NADA;
    }

    // ----------------------------
    // CLICK EN ARISTA
    // ----------------------------
    protected void manejarClickArista(Arista a, Line ui) {
        if (modoActual != ModoJuego.CONSTRUIR_CARRETERA) {
            System.out.println("No estás en modo carretera.");
            return;
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
}