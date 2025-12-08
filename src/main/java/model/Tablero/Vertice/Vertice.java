package model.Tablero.Vertice;

import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Arista.Arista;
import model.Tablero.Arista.Carretera;
import model.Tablero.Factory.ConectorVertices_MapaBasico;
import model.Tablero.Puerto.Puerto;
import model.Tablero.Puerto.SinPuerto;
import model.Tablero.Vertice.Estructura.Ciudad;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.NoHayEstructura;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    private Estructura estructura;
    private List<Arista> aristas;
    private int numeroDeVertice;
    private Estado estado;
    private Puerto puerto;

    //Para la visualizacion
    private double coordenadaX, coordenadaY;
    private boolean coordenadasAsignadas = false;

    public Vertice(int numeroDeVertice) {
        this.estructura = new NoHayEstructura();
        this.aristas = new ArrayList<Arista>();
        this.numeroDeVertice = numeroDeVertice;
        this.estado = new Vacio();
        this.puerto = new SinPuerto();
    }

    public Vertice() {
        this.estructura = new NoHayEstructura();
        this.aristas = new ArrayList<Arista>();
        this.numeroDeVertice = 0;
        this.estado = new Vacio();
        this.puerto = new SinPuerto();
    }

    public void setCoordenadas(double x, double y) {
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.coordenadasAsignadas = true;
    }

    public void setPuerto(Puerto puerto) {
        this.puerto = puerto;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public boolean tieneCoordenadasAsignadas() {
        return coordenadasAsignadas;
    }

    public int getNumeroDeVertice() {
        return numeroDeVertice;
    }

    public boolean estructuraEsDe(Jugador jugador) {
        return this.estructura.esDe(jugador);
    }

    public void bloquearse() {
        this.estado = new Bloqueado();
    }

    public void ocuparse(Estructura estructura) {
        this.estado = new Ocupado();
        this.estructura = estructura;
        this.estructura.sumarAJugador();
        this.puerto.darReglaAJugador(estructura.getJugador());
    }

    public void bloquearAdyacentes() {
        for (Arista arista : aristas) {
            arista.bloquearDestino();
        }
    }

    public void darRecurso(Recurso recurso) {
        this.estructura.entregarRecursos(recurso);
    }

    public boolean numeroDeVerticeEs(int numeroDeVertice) {
        return (this.numeroDeVertice == numeroDeVertice);
    }

    public static void agregarArista(Vertice vertice1, Vertice vertice2, int[] numeroDeArista) {

        Arista arista1 = new Arista(vertice2, numeroDeArista);
        Arista arista2 = new Arista(vertice1, numeroDeArista);

        vertice1.aristas.add(arista1);
        vertice2.aristas.add(arista2);

        arista1.setPar(arista2);
        arista2.setPar(arista1);

    }

    public static Vertice[] generarVertices() {

        Vertice[] vertices = new Vertice[54];
        for (int i = 0; i < 54; i++) {
            vertices[i] = new Vertice(i);
        }

        ConectorVertices_MapaBasico.generarGrafo(vertices);

        for (int i = 0; i < vertices.length; i++) {
            System.out.println(vertices[i].numeroDeVertice + " " +  vertices[i]);
        }

        return vertices;

    }

    //Ley de Demeter, cambiar esto a que el vertice/Estructura le avise al jugador que fue robado
    public List<Jugador> tieneDuenio(List<Jugador> jugadores) {
        return this.estructura.anotarDuenio(jugadores);
    }

    public void intentarUbicarCiudad(Ciudad ciudad) {
        this.estructura.intentarMejorar(ciudad, this);
    }

    public void ubicarEstructura(Estructura estructura) {
        estructura.ubicarseEnVerticeEnEstado(this.estado, this);
    }

    //Tratar de evitar estos metodos de abajo.

    public boolean permiteConstruccionDeAristaDe(Jugador jugador) {
        if(this.estructuraEsDe(jugador)) {
            return true;
        }
        for (Arista arista : aristas) {
            if (arista.esDe(jugador)){
                return true;
            }
        }
        return false;
    }

    public boolean validarConstruccionPara(Jugador jugador) {
        for (Arista arista : aristas) {
            if (arista.esDe(jugador)) {
                return true;
            }
        }
        return false;
    }

    public void ubicarCarretera(Carretera carretera, int[] numeroDeArista) {
        for (Arista arista : aristas) {
            arista.ubicarCarretera(carretera, numeroDeArista);
        }
    }

    public Estructura getEstructura(){
        return this.estructura;
    }

    public List<Arista> getAristas() {
        return this.aristas;
    }

}