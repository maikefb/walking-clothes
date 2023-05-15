package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Status;
import br.com.cwi.crescer.api.mapper.armario.BuscarArmarioResponseMapper;
import br.com.cwi.crescer.api.mapper.item.ItemArmarioResponseMapper;
import br.com.cwi.crescer.api.service.tagitem.ListarTagsDoItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ExibirPaginaDoArmarioServiceTest {

    @InjectMocks
    private ExibirPaginaDoArmarioService tested;

    @Mock
    private BuscarArmarioPorIdService buscarArmarioPorIdService;

    @Mock
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    @Mock
    private BuscarArmarioResponseMapper buscarArmarioResponseMapper;

    @Test
    public void deveExibirPaginaDoArmario() {
        int id = 0;
        Armario armario = new Armario();
        Item item = new Item();
        item.setStatus(Status.D);
        List<Item> itens = new ArrayList<>();
        itens.add(item);
        armario.setItens(itens);
        List<ItemArmarioResponse> itensResponse = new ArrayList<>();
        ItemArmarioResponse itemResponse = new ItemArmarioResponse();
        itensResponse.add(itemResponse);
        BuscarArmarioResponse buscarArmarioResponse = new BuscarArmarioResponse();

        Mockito.when(buscarArmarioPorIdService.buscar(id)).thenReturn(armario);
        Mockito.when(itemArmarioResponseMapper.apply(item)).thenReturn(itemResponse);
        Mockito.when(buscarArmarioResponseMapper.apply(armario, itensResponse)).thenReturn(buscarArmarioResponse);
        BuscarArmarioResponse result = tested.apply(id);

        Mockito.verify(buscarArmarioPorIdService).buscar(id);
        Mockito.verify(itemArmarioResponseMapper).apply(item);
        Mockito.verify(buscarArmarioResponseMapper).apply(armario, itensResponse);
        Assert.assertNotNull(result);
    }

}
