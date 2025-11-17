package Catan;

import org.junit.jupiter.api.Test;

public class CatanTest {

    @Test
    public void test01LosJugadoresRecibenRecursosIniciales(){
        Catan catan = new Catan();

        catan.prepararJugadores();
        catan.primeraEtapa();




    }

}
