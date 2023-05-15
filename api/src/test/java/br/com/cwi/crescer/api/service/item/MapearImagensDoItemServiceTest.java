package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.domain.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MapearImagensDoItemServiceTest {

    @InjectMocks
    private MapearImagensDoItemService tested;

    @Test
    public void deveMontarListaDeImagensAPartirDeListaDeStringComIdItemInformado() {
        List<String> imagens = new ArrayList<>();
        imagens.add("img");
        Item item = new Item();

        List<Imagem> result = tested.apply(imagens, item);
        Assert.assertEquals(imagens.get(0), result.get(0).getUrlImagem());
    }
}
