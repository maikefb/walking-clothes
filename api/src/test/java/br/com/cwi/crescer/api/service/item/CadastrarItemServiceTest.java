package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.request.item.CadastrarItemRequest;
import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.mapper.item.CadastrarItemRequestMapper;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import br.com.cwi.crescer.api.service.armario.BuscarArmarioPorIdService;
import br.com.cwi.crescer.api.service.tagestilo.BuscarTagEstiloPorNomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarItemServiceTest {

    @InjectMocks
    private CadastrarItemService tested;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CadastrarItemRequestMapper cadastrarItemRequestMapper;

    @Mock
    private BuscarTagEstiloPorNomeService buscarTagEstiloPorNomeService;

    @Mock
    private BuscarArmarioPorIdService buscarArmarioPorIdService;

    @Mock
    private MapearImagensDoItemService mapearImagensDoItemService;
    @Test
    public void deveCadastrarOItem() {
        CadastrarItemRequest request = new CadastrarItemRequest();
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        request.setTags(tags);
        Item item = new Item();
        List<Imagem> imagensDoItem = new ArrayList<>();
        Armario armario = new Armario();

        Mockito.when(cadastrarItemRequestMapper.apply(request)).thenReturn(item);
        Mockito.when(mapearImagensDoItemService.apply(request.getImgUrl(), item)).thenReturn(imagensDoItem);
        Mockito.when(buscarArmarioPorIdService.buscar(request.getIdArmario())).thenReturn(armario);
        tested.cadastrar(request);

        Mockito.verify(cadastrarItemRequestMapper).apply(request);
        Mockito.verify(mapearImagensDoItemService).apply(request.getImgUrl(), item);
        Mockito.verify(buscarArmarioPorIdService).buscar(request.getIdArmario());
        Mockito.verify(itemRepository).save(item);
    }
}
