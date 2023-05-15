package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.response.item.PaginaDoItemResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.mapper.item.PaginaItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPaginaItemServiceTest {

    @InjectMocks
    private BuscarPaginaItemService tested;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private PaginaItemMapper paginaItemMapper;

    @Test
    public void deveBuscarItemEMontarResponse() {
        int id = 0;
        Item item = new Item();
        PaginaDoItemResponse response = new PaginaDoItemResponse();

        Mockito.when(buscarItemPorIdService.find(id)).thenReturn(item);
        Mockito.when(paginaItemMapper.apply(item)).thenReturn(response);
        tested.find(id);

        Mockito.verify(buscarItemPorIdService).find(id);
        Mockito.verify(paginaItemMapper).apply(item);
    }
}
