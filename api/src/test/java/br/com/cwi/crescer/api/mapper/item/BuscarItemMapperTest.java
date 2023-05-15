package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.domain.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class BuscarItemMapperTest {

    @InjectMocks
    private BuscarItemMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearOBuscaItemResponse() {
        Item item = new Item();
        BuscaItemResponse itemResponse = new BuscaItemResponse();

        Mockito.when(modelMapper.map(item, BuscaItemResponse.class)).thenReturn(itemResponse);
        BuscaItemResponse result = tested.apply(item);

        Mockito.verify(modelMapper).map(item, BuscaItemResponse.class);
        Assert.assertEquals(item.getIdItem(), result.getIdItem());
        Assert.assertEquals(item.getTitulo(), result.getTitulo());
        Assert.assertEquals(item.getPreco(), result.getPreco());
        Assert.assertEquals(item.getTamanho(), result.getTamanho());
    }
}
