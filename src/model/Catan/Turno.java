package model.Catan;

import model.Errores.*;
import model.Errores.*;
import model.Tablero.Arista.Carretera;
import model.Tablero.Tablero;
import model.Tablero.Vertice.Estructura.Estructura;

public class Turno {

    private Tablero tablero;

    public Turno(Tablero tablero) {
        this.tablero = tablero;
    }

    public int intentarUbicarEstructura(Estructura estructura, int numeroDeVerice){
        //Para que tenga sentido, la eleccion del numero de vertice tiene que estar aca.
        try{
            this.tablero.ubicarEstructura(estructura, numeroDeVerice);
        } catch (VerticeNoVacio | VerticeVacio | VerticeOcupadoPorAlguienMas | VerticeOcupadoPorCiudad |
                 VerticeFueraDeAlcance e) {
            System.out.println("No se puede ubicar en un vertice");
            return this.intentarUbicarEstructura(estructura, numeroDeVerice);
        }
        return numeroDeVerice;
    }

    public void intentarUbicarCarretera(Carretera carretera, int[] numeroDeArista){
        try{
            this.tablero.ubicarCarretera(carretera, numeroDeArista);
        } catch (AristaEstaOcupada | AristaFueraDeAlcance e) {
            System.out.println("No se puede ubicar en un vertice");
            this.intentarUbicarCarretera(carretera, numeroDeArista);
        }
    }

}
