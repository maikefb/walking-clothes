package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.domain.TagItem;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.tagitem.TagItemRepository;
import br.com.cwi.crescer.api.service.tagestilo.BuscarTagEstiloPorNomeService;
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
public class MapearTagsDeEstiloDoItemServiceTest {

    @InjectMocks
    private MapearTagsDeEstiloDoItemService tested;

    @Mock
    private BuscarTagEstiloPorNomeService buscarTagEstiloPorNomeService;

    @Mock
    private TagItemRepository repository;

    @Test
    public void deveMontarListaDeTagsAPartirDeListaDeStringComIdItemInformado() {
        Item item = new Item();
        List<String> tags = new ArrayList<>();
        String nomeTag = "tag1";
        tags.add(nomeTag);
        TagEstilo tagEstilo = new TagEstilo();

        Mockito.when(buscarTagEstiloPorNomeService.apply(nomeTag)).thenReturn(tagEstilo);
        List<TagItem> result = tested.apply(tags, item);

        Mockito.verify(buscarTagEstiloPorNomeService).apply(nomeTag);
        Assert.assertEquals(nomeTag, result.get(0).getTagEstilo().getNome());
    }

    @Test
    public void deveMontarListaDeTagsAPartirDeListaDeStringComIdItemInformadoECriarTagNova() {
        Item item = new Item();
        List<String> tags = new ArrayList<>();
        String nomeTag = "tag1";
        tags.add(nomeTag);

        Mockito.when(buscarTagEstiloPorNomeService.apply(nomeTag)).thenThrow(RegistroNaoEncontradoException.class);
        List<TagItem> result = tested.apply(tags, item);

        Mockito.verify(buscarTagEstiloPorNomeService).apply(nomeTag);
        Assert.assertEquals(nomeTag, result.get(0).getTagEstilo().getNome());
    }
}
