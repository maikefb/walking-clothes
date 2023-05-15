package br.com.cwi.crescer.api.service.imagem;

import br.com.cwi.crescer.api.controller.request.imagem.AdicionarImagemRequest;
import br.com.cwi.crescer.api.controller.request.imagem.RemoverImagemRequest;
import br.com.cwi.crescer.api.domain.Imagem;
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
public class RemoverImagemDoItemServiceTest {

    @InjectMocks
    private RemoverImagemDoItemService tested;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private BuscarImagemPorIdService buscarImagemPorIdService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void deveAdicionarNovaImagemAoItem() {
        RemoverImagemRequest request = new RemoverImagemRequest();
        int idItem = 0;
        Item item = new Item();
        Imagem imagem = new Imagem();

        Mockito.when(buscarItemPorIdService.find(idItem)).thenReturn(item);
        Mockito.when(buscarImagemPorIdService.apply(request.getIdImagem())).thenReturn(imagem);
        Mockito.when(itemRepository.save(item)).thenReturn(item);
        tested.remover(request, idItem);

        Mockito.verify(buscarItemPorIdService).find(idItem);
        Mockito.verify(buscarImagemPorIdService).apply(request.getIdImagem());
        Mockito.verify(itemRepository).save(item);
    }

}
