package Terreno;

import model.Errores.DesiertoNoProduceNada;
import model.Recurso.*;
import model.Terreno.Desierto;
import model.Terreno.Productor.*;
import model.Terreno.Terreno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TerrenosTest {

    @Test
    public void test01ElBosqueProduceMadera(){
        Terreno bosque = new Bosque();

        Recurso recurso = bosque.darRecurso();

        Assertions.assertEquals(recurso.getClass(), Madera.class);
    }

    @Test
    public void test02ColinaProduceLadrillo(){
        Terreno colina = new Colina();

        Recurso recurso = colina.darRecurso();

        Assertions.assertEquals(recurso.getClass(), Ladrillo.class);
    }

    @Test
    public void test03CampoProduceTrigo(){
        Terreno campo = new Campo();

        Recurso recurso = campo.darRecurso();

        Assertions.assertEquals(recurso.getClass(), Trigo.class);
    }

    @Test
    public void test04ElPastizalProduceOveja(){
        Terreno pastizal = new Pastizal();

        Recurso recurso = pastizal.darRecurso();

        Assertions.assertEquals(recurso.getClass(), Oveja.class);
    }

    @Test
    public void test05MontañaProducePiedra(){
        Terreno montaña = new Montaña();

        Recurso recurso = montaña.darRecurso();

        Assertions.assertEquals(recurso.getClass(), Piedra.class);
    }

    @Test
    public void test06ElDesiertoNoProduce(){
        Terreno desierto = new Desierto();

        Assertions.assertThrows(DesiertoNoProduceNada.class, () -> {desierto.darRecurso();});
    }

}
