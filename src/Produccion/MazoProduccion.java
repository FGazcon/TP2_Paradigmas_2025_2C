package Produccion;

import Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class MazoProduccion {

    private List<MazoRecurso> recursos;
    private List<Carta> cartas = new ArrayList<Carta>();

    public MazoProduccion() {
        cartas = crearMazoParaBanco();
    }

    public boolean darRecurso(Recurso recurso){
        MazoRecurso mazo;
        for(MazoRecurso rec : recursos){
            if (rec.esDeTipo(recurso)) {
                mazo = rec;
                return mazo.darRecurso();
            }
        }
        return false;
    }

    public void recibirRecurso(Recurso recurso){
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

    }

    /////cambios

    public List<Carta> crearMazoParaBanco(){
        List<Carta> cartas = new ArrayList<Carta>();
        cartas.add(new Carta("MADERA"));
        cartas.add(new Carta("TRIGO"));
        cartas.add(new Carta("PIEDRA"));
        cartas.add(new Carta("LADRILLO"));
        cartas.add(new Carta("OVEJA"));
        return cartas;
    }

    public int longitud(){
        int cantidadDeRecursos = 0;
        for (MazoRecurso recurso : recursos) {
           cantidadDeRecursos = recurso.sumarRecursos(cantidadDeRecursos);
        }
    return cantidadDeRecursos;

    }

    public void consumirCartas(int cantidadDescarte){
        for(int i= 0; i< cantidadDescarte;i++){
            //this.recursos.remover(recursos.size());
        }
    }
}
