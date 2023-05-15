package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.service.imagem.ListarImagensItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ItemArmarioResponseMapperTest {

    @InjectMocks
    private ItemArmarioResponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ListarImagensItemService listarImagensItemService;

    @Test
    public void deveMapearOItemArmarioResponse() {
        Item item = new Item();
        ItemArmarioResponse itemArmarioResponse = new ItemArmarioResponse();
        List<String> imagens = new ArrayList<>();

        Mockito.when(modelMapper.map(item, ItemArmarioResponse.class)).thenReturn(itemArmarioResponse);
        Mockito.when(listarImagensItemService.apply(item.getImagens())).thenReturn(imagens);
        ItemArmarioResponse result = tested.apply(item);

        Mockito.verify(modelMapper).map(item, ItemArmarioResponse.class);
        Mockito.verify(listarImagensItemService).apply(item.getImagens());
        Assert.assertEquals(item.getIdItem(), result.getIdItem());
        Assert.assertEquals(item.getTitulo(), result.getTitulo());
        Assert.assertEquals(item.getTamanho(), result.getTamanho());
        Assert.assertEquals(item.getPreco(), result.getPreco());
    }
}
