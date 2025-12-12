package model.Tablero;

import model.Errores.DesiertoNoProduceNada;
import model.Errores.HexagonoBajoAsalto;
import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Arista.Carretera;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Vertice;
import model.Terreno.Terreno;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {

    private Vertice[] vertices;
    private final Terreno terreno;
    private final int numero;
    private EstadoHexagono estado;

    private double coordenadaX, coordenadaY;
    private boolean coordenadasAsignadas = false;

    public Hexagono(Terreno terreno, int numero) {
        this.vertices = new Vertice[6];
        this.terreno = terreno;
        this.numero = numero;
        this.estado = new Libre();
    }

    public void setCoordenadas(double x, double y) {
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.coordenadasAsignadas = true;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public Vertice[] getVertices() {
        return this.vertices;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public Terreno getNombre() {
        return  this.terreno;
    }

    public int getNumero() {
        return numero;
    }

    public boolean contieneVertice(int numeroDeVertice){
        for (Vertice vertex : vertices) {
            if (vertex.numeroDeVerticeEs(numeroDeVertice)) {
                return true;
            }
        }

        return false;
    }

    public void activarHexagonoParaVerticeEspecifico(int vertices_segundo_poblado) {

        for (Vertice vertice : vertices) {
            if (vertice.numeroDeVerticeEs(vertices_segundo_poblado)) {

                try{
                    Recurso recurso = this.terreno.darRecurso();
                    vertice.darRecurso(recurso);
                } catch (DesiertoNoProduceNada e) {
                    System.out.println("Se intento generar recursos del Desierto");
                }

            }
        }
    }

    public void activarHexagono(){
        this.estado.intentarProducir(this);
    }

    public void activarHexagonoParaNumero(int numero){
        if(this.numero == numero){
            try{
                this.estado.intentarProducir(this);
            } catch (HexagonoBajoAsalto e){
                System.out.println("Hexagono Bajo asalto no produce.");
            }
        }
    }

    public void producir(){
        for(Vertice vertice: vertices){
            Recurso recurso = this.terreno.darRecurso();
            vertice.darRecurso(recurso);
        }
    }

    private Vertice buscarVerticeNumero(int numeroDeVertice){
        for(Vertice vertice: vertices){
            if (vertice.numeroDeVerticeEs(numeroDeVertice)){
                return vertice;
            }
        }
        return null;
    }

    public void setVertices(Vertice[] vertices) {
        this.vertices = vertices;
    }

    public boolean esDesierto() {
        return this.numero == 7;
    }

    public void liberarse(){
        this.estado = new Libre();
    }

    public void recibirLadron(Jugador jugador){
        this.estado = new BajoAsalto();

        //Ley de Demeter, cambiar esto a que el vertice/Estructura le avise al jugador que fue robado
        List<Jugador> jugadores = new ArrayList<>();
        //chequera no se bloquea el hexagono
        for(Vertice vertice: vertices){
            vertice.tieneDuenio(jugadores);
        }
        System.out.println("roba jugador" + jugador.getNombre());
        System.out.println("cant jugadores " + jugadores.toArray().length );
        for (Jugador jugadorl : jugadores){

            System.out.println("jugador " + jugadorl.getNombre());
        }
        if(!jugadores.isEmpty()){
//            if (jugadores.contains(jugadorARobar)) {
//                jugadorARobar.dejarseRobarPorJugador(jugador);
//            }else{
                jugadores.getFirst().dejarseRobarPorJugador(jugador);
           // }
        }
    }

    public void ubicarEstructura(Estructura estructura, int numeroDeVertice) {
        Vertice verticeDestino = buscarVerticeNumero(numeroDeVertice);
        verticeDestino.ubicarEstructura(estructura);
    }

    public void ubicarCarretera(Carretera carretera, int[] numeroDeArista) {
        Vertice verticeOrigen = buscarVerticeNumero(numeroDeArista[0]);
        verticeOrigen.ubicarCarretera(carretera, numeroDeArista);
    }
}