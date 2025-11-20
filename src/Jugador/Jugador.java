package Jugador;

import Banco.Banco;
import Desarrollo.MazoDesarrolloGeneral;
import Errores.BancoNoTieneRecurso;
import Negociantes.Negociantes;
import Produccion.Carta;
import Produccion.MazoProduccion;
import Recurso.Recurso;
import Tablero.Tablero;
import Tablero.Vertice.Estructura.Poblado;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends Negociantes{

    private Banco banco;
    private MazoProduccion mazoProduccion;
    private List<Carta> cartas;
    private String nombre;

    public Jugador(String nombre, Banco banco) {
        this.cartas = new ArrayList<Carta>();
        this.banco = banco;
       // this.mazoProduccion = new MazoProduccion();
       // this.mazoDesarrolloGeneral = new MazoDesarrolloGeneral();
        this.nombre = nombre;
    }

    public void pedirAlBanco(Recurso recurso){
        cartas.add(banco.darRecurso(recurso));
    }
/*
    public int ubicarPoblado(Tablero tablero){

        Poblado poblado = new Poblado(this);

        Scanner sc = new Scanner(System.in);  // Crear Scanner para leer desde la terminal

        System.out.print("Ingresa un vertice: ");
        int numeroDeVerice = sc.nextInt();

        try{
            tablero.ubicarEstructura(poblado, numeroDeVerice);
        } catch (VerticeNoVacio e) {
            System.out.println("No se puede ubicar un vertice");
            return this.ubicarPoblado(tablero);
        }
        return numeroDeVerice;

    }
    */
    public int ubicarPoblado(Tablero tablero){
        int numeroDeVertice = this.elegirVertice();
        tablero.ubicarEstructura(new Poblado(this),numeroDeVertice);
        return numeroDeVertice;
    }
    public int ubicarPoblado2(Tablero tablero){
        int numeroDeVertice = this.elegirVertice2();
        tablero.ubicarEstructura(new Poblado(this),numeroDeVertice);
        return numeroDeVertice;
    }
/*
    public void descartarMitad(){
        if(this.cantidadCartas() > 7){
            this.mazoProduccion.consumirCartas(mazoProduccion.longitud()/2);
        }
    }

    public int cantidadCartas(){
        return mazoProduccion.longitud();
    }
*/
}
