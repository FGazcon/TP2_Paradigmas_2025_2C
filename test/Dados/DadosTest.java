package Dados;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DadosTest {

    @Test public void test01dadosDanEntre2Y12() {

        Dados dados = new Dados();
        int resultado = dados.tirarDados();
        Assertions.assertTrue(resultado < 13 && resultado > 1);

    }

    @Test public void test02DadosSeTiranMultiplesVecesYDanSiempreEnElRango(){
        Dados dados = new Dados();

        int[] lista1 = new int[5];
        int[] lista2 = new int[5];
        for (int i = 0; i < 5; i++) {
            lista1[i] = dados.tirarDados();
            lista2[i] = dados.tirarDados();
        }

        Assertions.assertNotEquals(lista1, lista2);

    }

}
