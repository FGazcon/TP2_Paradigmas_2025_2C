package Banco;

import Desarrollo.MazoDesarrolloGeneral;
import Jugador.Jugador;
import Recurso.*;

import java.util.HashMap;
import java.util.Map;

public class Banco {

    private MazoDesarrolloGeneral mazoDesarrollo;
    private Map<String, Recurso> mazoDeProduccion;
    private static Banco banco =  new Banco();

    public Banco(){
        this.mazoDeProduccion = Recurso.crearMazoProduccionBanco();
        this.mazoDesarrollo = MazoDesarrolloGeneral.generarMazoDesarrolloBanco();
    }

    public static Banco getBanco() {
        return banco;
    }

    public void sumarRecurso(String recurso, int cantidad){
        this.mazoDeProduccion.get(recurso).sumar(cantidad);
    }

    public void descartarRecurso(String recurso, int cantidad){
        this.mazoDeProduccion.get(recurso).descartar(cantidad);
    }

    public void tieneAlMenos(String recurso, int cantidad){
        this.mazoDeProduccion.get(recurso).tieneAlMenos(cantidad);
    }

    public void sumarRecurso(Piedra piedra, int cantidad){
        this.mazoDeProduccion.get("Piedra").sumar(cantidad);
    }

    public void sumarRecurso(Ladrillo ladrillo, int cantidad){
        this.mazoDeProduccion.get("Ladrillo").sumar(cantidad);
    }

    public void sumarRecurso(Oveja oveja, int cantidad){
        this.mazoDeProduccion.get("Oveja").sumar(cantidad);
    }

    public void sumarRecurso(Madera madera, int cantidad){
        this.mazoDeProduccion.get("Madera").sumar(cantidad);
    }

    public void sumarRecurso(Trigo trigo, int cantidad){
        this.mazoDeProduccion.get("Trigo").sumar(cantidad);
    }

    public void descartarRecurso(Trigo trigo, int cantidad){
        this.mazoDeProduccion.get("Trigo").descartar(cantidad);
    }

    public void descartarRecurso(Madera madera, int cantidad){
        this.mazoDeProduccion.get("Madera").descartar(cantidad);
    }

    public void descartarRecurso(Oveja oveja, int cantidad){
        this.mazoDeProduccion.get("Oveja").descartar(cantidad);
    }

    public void descartarRecurso(Piedra piedra, int cantidad){
        this.mazoDeProduccion.get("Piedra").descartar(cantidad);
    }

    public void descartarRecurso(Ladrillo ladrillo, int cantidad){
        this.mazoDeProduccion.get("Ladrillo").descartar(cantidad);
    }

    public boolean tieneAlMenos(Madera madera, int cantidad){
        return this.mazoDeProduccion.get("Madera").tieneAlMenos(cantidad);
    }

    public boolean tieneAlMenos(Ladrillo ladrillo, int cantidad){
        return this.mazoDeProduccion.get("Ladrillo").tieneAlMenos(cantidad);
    }

    public boolean tieneAlMenos(Piedra piedra, int cantidad){
        return this.mazoDeProduccion.get("Piedra").tieneAlMenos(cantidad);
    }

    public boolean tieneAlMenos(Oveja oveja, int cantidad){
        return this.mazoDeProduccion.get("Oveja").tieneAlMenos(cantidad);
    }

    public boolean tieneAlMenos(Trigo trigo, int cantidad){
        return this.mazoDeProduccion.get("Trigo").tieneAlMenos(cantidad);
    }

    public void jugadorLeSolicitaRecurso(Jugador jugador, Madera madera, int cantidad){
        if(mazoDeProduccion.get("Madera").tieneAlMenos(cantidad)){
            madera.transferirAJugador(jugador, cantidad);
            madera.descartarAlBanco(this, cantidad);
        }
    }
    public void jugadorLeSolicitaRecurso(Jugador jugador, Ladrillo ladrillo, int cantidad){
        if(mazoDeProduccion.get("").tieneAlMenos(cantidad)){
            ladrillo.transferirAJugador(jugador, cantidad);
            ladrillo.descartarAlBanco(this, cantidad);
        }
    }
    public void jugadorLeSolicitaRecurso(Jugador jugador, Oveja oveja, int cantidad){
        if(mazoDeProduccion.get("").tieneAlMenos(cantidad)){
            oveja.transferirAJugador(jugador, cantidad);
            oveja.descartarAlBanco(this, cantidad);
        }
    }
    public void jugadorLeSolicitaRecurso(Jugador jugador, Trigo trigo, int cantidad){
        if(mazoDeProduccion.get("").tieneAlMenos(cantidad)){
            trigo.transferirAJugador(jugador, cantidad);
            trigo.descartarAlBanco(this, cantidad);
        }
    }
    public void jugadorLeSolicitaRecurso(Jugador jugador, Piedra piedra, int cantidad){
        if(mazoDeProduccion.get("").tieneAlMenos(cantidad)){
            piedra.transferirAJugador(jugador, cantidad);
            piedra.descartarAlBanco(this, cantidad);
        }
    }
}
