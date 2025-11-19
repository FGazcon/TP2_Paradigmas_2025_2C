package Produccion;

import java.util.ArrayList;
import java.util.List;

public class MazoProduccion {

    private List<MazoRecurso> recursos;

    public MazoProduccion() {
        recursos = new ArrayList<MazoRecurso>();
    }

    public static MazoProduccion crearMazoParaBanco(){
        //Revisar si es responsabilidad del MazoProduccion o de la clase MazoRecurso
        MazoProduccion mazo = new MazoProduccion();

        mazo.recursos.add(new MazoRecurso(new Madera(), 19));
        mazo.recursos.add(new MazoRecurso(new Trigo(), 19));
        mazo.recursos.add(new MazoRecurso(new Ladrillo(), 19));
        mazo.recursos.add(new MazoRecurso(new Piedra(), 19));
        mazo.recursos.add(new MazoRecurso(new Oveja(), 19));

        return mazo;

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
            System.out.println(i + " " + recursos.get(i) + " " + recursos.get(i).getCantidad());
        }

    }

}
