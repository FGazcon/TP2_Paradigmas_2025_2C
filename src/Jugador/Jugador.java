package Jugador;

import Banco.Banco;
import Desarrollo.MazoDesarrolloGeneral;
import Errores.BancoNoTieneRecurso;
import Errores.VerticeNoVacio;
import Negociantes.Negociantes;
import Produccion.MazoProduccion;
import Produccion.Recurso;
import Tablero.Tablero;
import Tablero.Vertice.Estructura.Poblado;

import java.util.Scanner;

public class Jugador extends Negociantes{

    private Banco banco;
    private MazoProduccion mazoProduccion;
    private MazoDesarrolloGeneral mazoDesarrolloGeneral;
    private String nombre;

    public Jugador(String nombre, Banco banco) {
        this.banco = banco;
        this.mazoProduccion = new MazoProduccion();
        this.mazoDesarrolloGeneral = new MazoDesarrolloGeneral();
        this.nombre = nombre;
    }

    public void pedirAlBanco(Recurso recurso){
        if (this.banco.darRecurso(recurso)){
            this.mazoProduccion.recibirRecurso(recurso);
        } else {
            throw new BancoNoTieneRecurso();
        }
    }

    public boolean equals(Jugador jugador){
        return this.nombre.equals(jugador.nombre);
    }

}
