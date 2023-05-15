package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.request.item.EditarItemRequest;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.mapper.item.EditarItemMapper;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EditarItemServiceTest {

    @InjectMocks
    private EditarItemService tested;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private EditarItemMapper editarItemMapper;

    @Mock
    private ItemRepository repository;

    @Test
    public void deveEditarOItemDoIdInformado() {
        EditarItemRequest request = new EditarItemRequest();
        int id = 0;
        Item item = new Item();

        Mockito.when(buscarItemPorIdService.find(id)).thenReturn(item);
        Mockito.when(editarItemMapper.apply(item, request)).thenReturn(item);
        tested.apply(request, id);

        Mockito.verify(buscarItemPorIdService).find(id);
        Mockito.verify(editarItemMapper).apply(item, request);
        Mockito.verify(repository).save(item);
    }
}
