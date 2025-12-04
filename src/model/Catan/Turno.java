package model.Catan;

import model.Dados.Dados;
import model.Errores.*;
import model.Errores.*;
import model.Tablero.Arista.Carretera;
import model.Jugador.Jugador;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Estructura;
import model.Tablero.Vertice.Estructura.PobladoInicial;

public abstract class Turno {

    protected Tablero tablero;
    protected Jugador jugador;
    protected Catan catan;
    protected Dados dados;

    public Turno(Catan catan, Tablero tablero, Jugador jugador, Dados dados){
        this.tablero = tablero;
        this.jugador = jugador;
        this.catan = catan;
        this.dados = dados;
    }

    public boolean intentarUbicarEstructura(Estructura estructura, int numeroDeVerice){
        //Para que tenga sentido, la eleccion del numero de vertice tiene que estar aca.
        try{
            this.tablero.ubicarEstructura(estructura, numeroDeVerice);
            return true;
        } catch (VerticeNoVacio | VerticeVacio | VerticeOcupadoPorAlguienMas | VerticeOcupadoPorCiudad |
                 VerticeFueraDeAlcance e) {
            System.out.println("No se puede ubicar en un vertice");
            return false;
        }
    }

    public boolean intentarUbicarCarretera(Carretera carretera, int[] numeroDeArista){
        try{
            this.tablero.ubicarCarretera(carretera, numeroDeArista);
            return true;
        } catch (AristaEstaOcupada | AristaFueraDeAlcance e) {
            System.out.println("No se puede ubicar en un vertice");
            this.intentarUbicarCarretera(carretera, numeroDeArista);
            return false;
        }
    }

    public abstract void construirCarretera(int[] numeroDeArista);

    public abstract void construirPoblado(int numeroDeVertice);

    public void construirCiudad(int numeroDeVertice) {
        throw new UnsupportedOperationException("No se puede construir ciudad en este tipo de turno.");
    }


    //fijarse si hace falta que el Turno este metido en todo esto.
    public Turno terminarTurno(AdministradorDeJugadores administrador){
        jugador.actualizarTurnoCartas();
        return administrador.nuevoTurno(catan, tablero, dados);
    }

    public Jugador getJugadorActual(){
        return this.jugador;
    }

}
