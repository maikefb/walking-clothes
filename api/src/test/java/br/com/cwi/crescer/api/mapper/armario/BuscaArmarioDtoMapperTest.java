package br.com.cwi.crescer.api.mapper.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscaArmarioDtoResponse;
import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.dto.BuscaArmarioDto;
import br.com.cwi.crescer.api.dto.BuscaArmarioDtoImpl;
import br.com.cwi.crescer.api.dto.BuscaItemDto;
import br.com.cwi.crescer.api.dto.BuscaItemDtoImpl;
import br.com.cwi.crescer.api.service.armario.ContarItensAVendaArmarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscaArmarioDtoMapperTest {

    @InjectMocks
    private BuscaArmarioDtoMapper tested;

    @Mock
    private ContarItensAVendaArmarioService contarItensAVendaArmarioService;

    @Test
    public void test() {
        Integer id = 0;
        String titulo = "titulo";
        String nome = "nome1";
        double latitude = -30.025134;
        double longitude= -51.202820;
        Integer qtdItensAVenda = 1;
        BuscaArmarioDto armario = new BuscaArmarioDtoImpl(id,titulo,"tag1",nome,"cidade",latitude,longitude);

        List<BuscaItemResponse> itens = new ArrayList<>();
        double distancia = 0.0;

        Mockito.when(contarItensAVendaArmarioService.apply(id)).thenReturn(qtdItensAVenda);
        BuscaArmarioDtoResponse result = tested.apply(armario,itens,distancia);


        Mockito.verify(contarItensAVendaArmarioService).apply(id);
        Assert.assertEquals(armario.getIdArmario(),result.getIdArmario());

    }
}
