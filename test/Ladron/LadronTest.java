package Ladron;

import Tablero.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LadronTest {

    @Test
    public void test01LadronSeMueve(){

        Ladron ladron = Ladron.getLadron();

        ladron.moverLadron(14);

        int ubicacionLadron = ladron.moverLadron(2);

        Assertions.assertEquals(ubicacionLadron,14);

    }

}
