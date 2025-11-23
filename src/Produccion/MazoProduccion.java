package Produccion;

import Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class MazoProduccion {

    private List<List<Carta>> mazosPorTipo = new ArrayList<>();


    public MazoProduccion() {
        mazosPorTipo = crearMazoParaBanco();
    }

    public List<Carta> encontrarTipoCarta(Recurso recurso){
        for(List<Carta> mazo : mazosPorTipo) {
           if(mazo.getFirst().cartaEncontrada(recurso)){
               return mazo;
           }
        }
        return null;
    }


    private Carta eliminarCartaRecurso(List<Carta> cartas) {
        return cartas.removeLast();
    }


    /////cambios
    public boolean darRecurso(Recurso recurso){
        return encontrarTipoCarta(recurso) != null;

    }
    private Carta buscarEliminarCartaRecurso(Recurso recurso){
        List<Carta> mazoRecurso ;
        mazoRecurso = encontrarTipoCarta(recurso);
        return eliminarCartaRecurso(mazoRecurso);
    }
    public Carta recibirRecurso(Recurso recurso){
        if(darRecurso(recurso)){
            return buscarEliminarCartaRecurso(recurso);

        }
        return null;
    }


    public  List<Carta> mazoDeMadera() {
        List<Carta> cartas= new ArrayList<>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("MADERA"));
        }
        return cartas;
    }
    public  List<Carta> mazoDeTrigo() {
        List<Carta> cartas= new ArrayList<>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("TRIGO"));
        }
        return cartas;
    }
    public  List<Carta> mazoDeOveja() {
        List<Carta> cartas= new ArrayList<>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("OVEJA"));
        }
        return cartas;
    }
    public  List<Carta> mazoDeLadrillo() {
        List<Carta> cartas= new ArrayList<>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("LADRILLO"));
        }
        return cartas;
    }
    public  List<Carta> mazoDePiedra() {
        List<Carta> cartas= new ArrayList<>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("PIEDRA"));
        }
        return cartas;
    }

    public List<List<Carta>> crearMazoParaBanco(){
         List<List<Carta>> mazosPorTipo = new ArrayList<>();
        mazosPorTipo.add(mazoDePiedra());
        mazosPorTipo.add(mazoDeLadrillo());
        mazosPorTipo.add(mazoDeMadera());
        mazosPorTipo.add(mazoDeOveja());
        mazosPorTipo.add(mazoDeTrigo());

        return mazosPorTipo;
    }
}
