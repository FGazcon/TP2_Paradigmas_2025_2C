package Jugador;

import Banco.Banco;
import Comercio.ReglaDeComercio;
import Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private List<Recurso> recursos;
    private String nombre;
    private int puntaje;
    private ReglaDeComercio reglaDeComercio;

    public Jugador(String nombre) {
        this.recursos = new ArrayList<Recurso>();
        this.nombre = nombre;
        this.puntaje = 0;
    }

    public void pedirAlBanco(Recurso recurso){

        Banco banco = Banco.getBanco();
        if(banco.darRecurso(recurso)){
            this.recursos.add(recurso);
        }

    }

    public void imprimirRecursos(){
        for (Recurso recurso : this.recursos){
            System.out.println(recurso);
        }
    }

    public void descartarMitad(){
        if(this.cantidadCartas() > 7) {
            descartarPorLadron(cantidadCartas() / 2);
        }
    }

    public void dejarseRobarPorJugador(int cantidadCartasADescartar, Jugador jugador){

        Banco banco = Banco.getBanco();
        //descarta la ultima carta
        for (int i = 0; i < cantidadCartasADescartar; i++) {

            Recurso recursoDescartado = this.recursos.removeLast();
            banco.recibirRecurso(recursoDescartado);
            jugador.pedirAlBanco(recursoDescartado);

        }
    }

    public void descartarPorLadron(int cantidadCartasADescartar){
        Banco banco = Banco.getBanco();
        for (int i = 0; i < cantidadCartasADescartar; i++) {
            banco.recibirRecurso(this.recursos.removeLast());
        }
    }

    public int cantidadCartas(){
        return this.recursos.size();
    }

    public void enviarAJugador(Jugador jugador, List<Recurso> recursos) {
        Banco banco = Banco.getBanco();
        for (Recurso recurso : recursos) {
            banco.recibirRecurso(recurso);
            jugador.pedirAlBanco(recurso);
        }
    }

    public void sumarPunto(){
        this.puntaje++;
    }

    public int calcularPuntaje() {
        int puntaje = this.puntaje;
        //Sumar PV
        //Sumar Cartas especiales
        return puntaje;
    }

    public void recibir(Recurso recurso) {
        this.recursos.add(recurso);
    }

    private boolean puedeEntregar(List<Recurso> ofrecidos) {
        List<Recurso> copiaListaRecursos = new ArrayList<>(this.recursos);
        for (Recurso r : ofrecidos) {
            if (!copiaListaRecursos.remove(r)) return false;
        }
        return true;
    }

    private void entregarA(Jugador otro, List<Recurso> ofrecidos) {
        for (Recurso r : ofrecidos) {
            this.recursos.remove(r);
            otro.recibir(r);
        }
    }

    private void recibirDe(Jugador otro, List<Recurso> pedidos) {
        for (Recurso r : pedidos) {
            otro.recursos.remove(r);
            this.recursos.add(r);
        }
    }

    public boolean comerciarConJugador(Jugador otroJugador, List<Recurso> ofrecidos, List<Recurso> pedidos) {

        if (!this.puedeEntregar(ofrecidos)) return false;
        if (!otroJugador.puedeEntregar(pedidos)) return false;

        this.entregarA(otroJugador, ofrecidos);
        this.recibirDe(otroJugador, pedidos);

        return true;
    }

    // Solamente para testear
    public boolean tiene(Recurso recurso) {
        return recursos.contains(recurso);
    }

    public boolean puedeEntregarNDelMismoTipo(Recurso recurso, int cantidad) {
        int contadorDeRecursos = 0;

        for (Recurso r : recursos) {
            if (r.getClass() == recurso.getClass()) contadorDeRecursos++;
        }

        return contadorDeRecursos >= cantidad;
    }

    public void entregarNRecursosAlBanco(Recurso recurso, int cantidad) {
        Banco banco = Banco.getBanco();

        int recursosEntregados = 0;

        for (int i = 0; i < cantidadCartas() && recursosEntregados < cantidad; i++) {
            if (recursos.get(i).getClass() == recurso.getClass()) {
                Recurso r = recursos.remove(i);
                banco.recibirRecurso(r);
                recursosEntregados++;
                i--;
            }
        }
    }

    public void recibirRecursoDesdeBanco(Recurso recurso) {
        recibir(recurso);
    }

    public boolean comerciarConBanco(Recurso ofrecido, Recurso pedido) {
        Banco banco = Banco.getBanco();
        return banco.intercambiarConJugador(this, pedido, ofrecido);
    }

    public boolean tieneCantidadDe(Recurso recurso, int cantidad) {
        int contador = 0;
        for (Recurso r : this.recursos) {
            if (r.getClass().equals(recurso.getClass())) contador++;
            if (contador == cantidad) return true;
        }
        return false;
    }

    public void entregarRecurso(Recurso recurso, int cantidad) {
        int recursosRemovidos = 0;
        for (int i = 0; i < recursos.size() && recursosRemovidos < cantidad; i++) {
            if (recursos.get(i).getClass().equals(recurso.getClass())) {
                recursos.remove(i);
                recursosRemovidos++;
                i--;
            }
        }
    }

    public void asignarReglaDeComercio(ReglaDeComercio regla) {
        this.reglaDeComercio = regla;
    }

    public boolean comerciarEnPuerto(Recurso ofrecido, Recurso pedido) {
        if (reglaDeComercio == null) return false;
        if (!reglaDeComercio.puedeHacerComercioMaritimo(this, ofrecido)) return false;

        reglaDeComercio.realizarComercioMaritimo(this, ofrecido, pedido);
        return true;
    }

}
