package br.com.cwi.crescer.api.mapper.endereco;

import br.com.cwi.crescer.api.domain.Endereco;
import br.com.cwi.crescer.api.domain.Municipio;
import br.com.cwi.crescer.api.service.municipio.BuscarMunicipioPorNomeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoMapperTest {

    @InjectMocks
    private EnderecoMapper tested;

    @Mock
    private BuscarMunicipioPorNomeService buscarMunicipioPorNomeService;

    @Test
    public void deveMapearOEndereco() {
        String rua = "";
        String cidade = "";
        BigDecimal latitude = new BigDecimal("0");
        BigDecimal longitude = new BigDecimal("0");
        Municipio municipio = new Municipio();

        Mockito.when(buscarMunicipioPorNomeService.apply(cidade)).thenReturn(municipio);
        Endereco endereco = tested.apply(rua, cidade, latitude, longitude);

        Mockito.verify(buscarMunicipioPorNomeService).apply(cidade);
        Assert.assertEquals(rua, endereco.getRua());
        Assert.assertEquals(municipio, endereco.getCidade());
        Assert.assertEquals(latitude, endereco.getLatitude());
        Assert.assertEquals(longitude, endereco.getLongitude());
    }
}
