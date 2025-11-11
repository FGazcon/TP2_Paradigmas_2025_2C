package Dados;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;


public class DadosTest {

    @Test public void dadosDanEntre2Y12() {

        Dados dados = new Dados();
        int resultado = dados.tirarDados();
        Assertions.assertTrue(resultado < 13 && resultado > 1);

    }

}
