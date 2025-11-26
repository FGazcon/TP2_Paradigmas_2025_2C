package Produccion;

import Recurso.*;

import java.util.*;

public class MazoProduccion {

    private HashMap<Class<? extends Recurso>, List<Recurso>> mapaRecursos;

    public MazoProduccion() {
        mapaRecursos = new HashMap<Class<? extends Recurso>, List<Recurso>>();
    }

    public <T extends Recurso> void añadirRecurso(T recurso){
        //Compute if absent trata de encontrar la llave, si no existe la registra con el valor a la derecha
        //es decir, busca la clase de recurso, si no esta, le genera una lista vacia. Luego le añade el recurso.
        //https://www.w3schools.com/java/java_ref_hashmap.asp tiene todos los metodos del hashmap

        mapaRecursos.computeIfAbsent(recurso.getClass(), k-> new ArrayList<>()).add(recurso);
    }

    public <T extends Recurso> boolean pedirRecurso(T recurso){
        List<Recurso> lista = mapaRecursos.computeIfAbsent(recurso.getClass(),  k -> new ArrayList<>());
        if(lista.isEmpty()){
            return false;
        }
        lista.removeFirst();
        return true;
    }

    public <T extends Recurso> int cantidadPorTipo(Class<T> tipo) {
        return mapaRecursos.getOrDefault(tipo, Collections.emptyList()).size();
    }

    public void mostrarInventario() {
        mapaRecursos.forEach((tipo, lista) ->
                System.out.println(tipo.getSimpleName() + ": " + lista.size())
        );
    }

    public static List<Recurso> mazoDeMadera() {
        List<Recurso> maderas= new ArrayList<>();
        for(int i=0;i<19;i++){
            maderas.add(new Madera());
        }
        return maderas;
    }

    public static List<Recurso> mazoDeTrigo() {
        List<Recurso> trigos= new ArrayList<>();
        for(int i=0;i<19;i++){
            trigos.add(new Trigo());
        }
        return trigos;
    }

    public static List<Recurso> mazoDeOveja() {
        List<Recurso> ovejas= new ArrayList<>();
        for(int i=0;i<19;i++){
            ovejas.add(new Oveja());
        }
        return ovejas;
    }

    public static List<Recurso> mazoDeLadrillo() {
        List<Recurso> ladrillos= new ArrayList<>();
        for(int i=0;i<19;i++){
            ladrillos.add(new Ladrillo());
        }
        return ladrillos;
    }

    public static List<Recurso> mazoDePiedra() {
        List<Recurso> piedras= new ArrayList<>();
        for(int i=0;i<19;i++){
            piedras.add(new Piedra());
        }
        return piedras;
    }

    public static MazoProduccion crearMazoParaBanco(){
        HashMap<Class<? extends Recurso>, List<Recurso>> mazosPorTipo = new HashMap();
        mazosPorTipo.put(Piedra.class, mazoDePiedra());
        mazosPorTipo.put(Ladrillo.class, mazoDeLadrillo());
        mazosPorTipo.put(Madera.class, mazoDeMadera());
        mazosPorTipo.put(Oveja.class, mazoDeOveja());
        mazosPorTipo.put(Trigo.class, mazoDeTrigo());

        MazoProduccion mazo = new MazoProduccion();
        mazo.mapaRecursos =  mazosPorTipo;

        mazo.mostrarInventario();

        return mazo;
    }
}
