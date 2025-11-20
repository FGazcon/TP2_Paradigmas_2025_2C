package Produccion;

import Recurso.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MazoProduccion {

    private List<List<Carta>> mazosPorTipo = new ArrayList<>();


    public MazoProduccion() {
        mazosPorTipo = crearMazoParaBanco();
    }

    public void recibirRecurso(Recurso recurso){

        //no entiendo que hace esto
        MazoRecurso mazo;
        int encontrado = 0;
        while(encontrado < recursos.size() && encontrado >= -1){
            if (this.recursos.get(encontrado).esDeTipo(recurso)){
                mazo = this.recursos.get(encontrado);
                mazo.recibirRecurso();
                return;
            }
            encontrado++;
        }
        this.recursos.add(new MazoRecurso(recurso, 1));

        for (int i = 0; i < recursos.size(); i++) {
            System.out.println(i + " " + recursos.get(i));
        }


        for(Carta carta: this.cartas){
            if(carta.cartaEncontrada(recurso)){
                eliminarCartaRecurso(recurso);
            }
        }


    }
    public List<Carta> encontrarTipoCarta(Recurso recurso){
        for(List<Carta> mazo : mazosPorTipo) {
           if(mazo.contains(recurso)){
               return mazo;
           }
        }
        return null;
    }

    void eliminarCartaRecurso(List<Carta> cartas) {
        cartas.removeFirst();
    }


    /////cambios


    public List<Carta> mazoDeMadera() {
        List<Carta> cartas= new ArrayList<Carta>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("MADERA"));
        }
        return cartas;
    }
    public List<Carta> mazoDeTrigo() {
        List<Carta> cartas= new ArrayList<Carta>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("TRIGO"));
        }
        return cartas;
    }
    public List<Carta> mazoDeOveja() {
        List<Carta> cartas= new ArrayList<Carta>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("OVEJA"));
        }
        return cartas;
    }
    public List<Carta> mazoDeLadrillo() {
        List<Carta> cartas= new ArrayList<Carta>();
        for(int i=0;i<19;i++){
            cartas.add(new Carta("LADRILLO"));
        }
        return cartas;
    }
    public List<Carta> mazoDePiedra() {
        List<Carta> cartas= new ArrayList<Carta>();
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
