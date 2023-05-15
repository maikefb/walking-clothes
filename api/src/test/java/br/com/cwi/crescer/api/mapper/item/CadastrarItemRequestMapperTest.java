package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.request.item.CadastrarItemRequest;
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
public class CadastrarItemRequestMapperTest {

    @InjectMocks
    private CadastrarItemRequestMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearOItem() {
        CadastrarItemRequest request = new CadastrarItemRequest();
        request.setTipo("BERMUDA");
        request.setCor("VERDE");
        request.setEstadoUso("N");
        Item item = new Item();

        Mockito.when(modelMapper.map(request, Item.class)).thenReturn(item);
        Item result = tested.apply(request);

        Mockito.verify(modelMapper).map(request, Item.class);
        Assert.assertEquals(request.getTitulo(), result.getTitulo());
        Assert.assertEquals(request.getDescricao(), result.getDescricao());
        Assert.assertEquals(request.getTamanho(), result.getTamanho());
        Assert.assertEquals(request.getBusto(), result.getBusto().doubleValue(), 0);
        Assert.assertEquals(request.getComprimento(), result.getComprimento().doubleValue(), 0);
        Assert.assertEquals(request.getQuadril(), result.getQuadril().doubleValue(), 0);
        Assert.assertEquals(request.getTipo(), result.getTipo().name());
        Assert.assertEquals(request.getCor(), result.getCor().name());
        Assert.assertEquals(request.getMarca(), result.getMarca());
        Assert.assertEquals(request.getEstadoUso(), result.getEstadoUso().name());
        Assert.assertEquals(request.getPreco(), result.getPreco().doubleValue(), 0);
        Assert.assertEquals(request.getPrecoOriginal(), result.getPrecoOriginal().doubleValue(), 0);
        Assert.assertEquals(request.isAceitaTroca(), result.isAceitaTroca());
    }
}
