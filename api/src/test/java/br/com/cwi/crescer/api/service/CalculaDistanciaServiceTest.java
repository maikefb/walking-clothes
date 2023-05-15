package br.com.cwi.crescer.api.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculaDistanciaServiceTest {

    @InjectMocks
    private CalculaDistanciaService tested;

    @Test
    public void deveCalcularADistanciaEntreDuasCoordenadas() {
        double lat1 = -29.733263110080546;
        double long1 = -51.15539785425275;
        double lat2 = -29.733827160659832;
        double long2 = -51.15097715278402;
        double distanciaEsperada = 0.43190;

        double result = tested.apply(lat1, long1, lat2, long2);

        Assert.assertEquals(distanciaEsperada, result, 0.1);
    }

}
