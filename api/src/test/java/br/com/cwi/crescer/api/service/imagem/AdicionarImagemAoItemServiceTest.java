package br.com.cwi.crescer.api.service.imagem;

import br.com.cwi.crescer.api.controller.request.imagem.AdicionarImagemRequest;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdicionarImagemAoItemServiceTest {

    @InjectMocks
    private AdicionarImagemAoItemService tested;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void deveAdicionarNovaImagemAoItem() {
        AdicionarImagemRequest request = new AdicionarImagemRequest();
        int idItem = 0;
        Item item = new Item();

        Mockito.when(buscarItemPorIdService.find(idItem)).thenReturn(item);
        Mockito.when(itemRepository.save(item)).thenReturn(item);
        tested.add(request, idItem);

        Mockito.verify(buscarItemPorIdService).find(idItem);
        Mockito.verify(itemRepository).save(item);
    }

}
