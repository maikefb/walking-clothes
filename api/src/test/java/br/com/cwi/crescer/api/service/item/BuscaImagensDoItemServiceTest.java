package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.response.imagem.ImagemResponse;
import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.mapper.imagem.ImagemResponseMapper;
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
public class BuscaImagensDoItemServiceTest {

    @InjectMocks
    private BuscaImagensDoItemService tested;

    @Mock
    private BuscarItemPorIdService buscarItem;

    @Mock
    private ImagemResponseMapper mapper;

    @Test
    public void deveRetornarListaDeImagemResponse() {

        Integer id = 1;
        List<Imagem> imagens = new ArrayList<>();

        List<ImagemResponse> imagemResponses = new ArrayList<>();
        imagemResponses.add(new ImagemResponse());
        imagemResponses.add(new ImagemResponse());

        Item item = new Item();
        item.setImagens(imagens);


        Mockito.when(buscarItem.find(id)).thenReturn(item);
        Mockito.when(mapper.apply(item.getImagens())).thenReturn(imagemResponses);

        List<ImagemResponse> result = tested.apply(id);


        Mockito.verify(buscarItem).find(id);
        Mockito.verify(mapper).apply(item.getImagens());
        Assert.assertEquals(imagemResponses.size(), result.size());

    }
}
