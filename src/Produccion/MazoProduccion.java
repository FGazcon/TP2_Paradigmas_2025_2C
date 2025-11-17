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

        mazo.recursos.add(new MazoRecurso(Recurso.MADERA, 19));
        mazo.recursos.add(new MazoRecurso(Recurso.TRIGO, 19));
        mazo.recursos.add(new MazoRecurso(Recurso.PIEDRA, 19));
        mazo.recursos.add(new MazoRecurso(Recurso.LADRILLO, 19));
        mazo.recursos.add(new MazoRecurso(Recurso.OVEJA, 19));

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
        while(encontrado >= 0){
            if (this.recursos.get(encontrado).esDeTipo(recurso)){
                mazo = this.recursos.get(encontrado);
                mazo.recibirRecurso();
                return;
            }
            encontrado++;
        }
        this.recursos.add(new MazoRecurso(recurso, 1));

    }

}
