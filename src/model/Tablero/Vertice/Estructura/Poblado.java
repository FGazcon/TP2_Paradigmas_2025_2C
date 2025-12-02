package model.Tablero.Vertice.Estructura;

import model.Errores.VerticeFueraDeAlcance;
import model.Errores.VerticeNoVacio;
import model.Errores.VerticeOcupadoPorAlguienMas;
import model.Jugador.Jugador;
import model.Recurso.Recurso;
import model.Tablero.Vertice.Estado;
import model.Tablero.Vertice.Vertice;

import java.util.List;

public class Poblado extends Estructura{

    @Override
    public void ubicarseEnVerticeEnEstado(Estado estado, Vertice vertice) {
        if (vertice.validarConstruccionPara(this.jugador)){
            estado.intentarUbicarEstructura(this, vertice);
        } else {
            throw new VerticeFueraDeAlcance();
        }
    }

    @Override
    public void entregarRecursos(Recurso recurso) {
        recurso.hacerQuejugadorSoliciteABanco(this.jugador, 1);
    }

    public Poblado(Jugador jugador){
        this.jugador = jugador;
    }

    @Override
    public List<Jugador> anotarDuenio(List<Jugador> jugadores) {
        jugadores.add(this.jugador);
        return jugadores;
    }

    @Override
    public void intentarMejorar(Ciudad estructura, Vertice vertice) {
        if(estructura.esDe(this.jugador)){
            vertice.ocuparse(estructura);
        } else {
            throw new VerticeOcupadoPorAlguienMas();
        }
    }

    @Override
    public void intentarMejorar(Poblado estructura, Vertice vertice) {
        throw new VerticeNoVacio();
    }

}