package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.request.item.EditarItemRequest;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.service.armario.BuscarArmarioPorIdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EditarItemMapperTest {

    @InjectMocks
    private EditarItemMapper tested;

    @Mock
    private BuscarArmarioPorIdService buscarArmarioPorIdService;

    @Test
    public void deveMapearOItemEditado() {
        Item item = new Item();
        EditarItemRequest request = new EditarItemRequest();
        request.setTipo("BERMUDA");
        request.setCor("VERDE");
        request.setEstadoUso("N");
        Armario armario = new Armario();

        Mockito.when(buscarArmarioPorIdService.buscar(request.getIdNovoArmario())).thenReturn(armario);
        Item result = tested.apply(item, request);

        Mockito.verify(buscarArmarioPorIdService).buscar(request.getIdNovoArmario());
        Assert.assertEquals(request.getTitulo(), result.getTitulo());
        Assert.assertEquals(request.getDescricao(), result.getDescricao());
        Assert.assertEquals(request.getTamanho(), result.getTamanho());
        Assert.assertEquals(request.getBusto(), result.getBusto());
        Assert.assertEquals(request.getComprimento(), result.getComprimento());
        Assert.assertEquals(request.getQuadril(), result.getQuadril());
        Assert.assertEquals(request.getTipo(), result.getTipo().name());
        Assert.assertEquals(request.getCor(), result.getCor().name());
        Assert.assertEquals(request.getMarca(), result.getMarca());
        Assert.assertEquals(request.getEstadoUso(), result.getEstadoUso().name());
        Assert.assertEquals(request.getPreco(), result.getPreco());
        Assert.assertEquals(request.getPrecoOriginal(), result.getPrecoOriginal());
        Assert.assertEquals(request.isAceitaTroca(), result.isAceitaTroca());
        Assert.assertSame(armario, result.getIdArmario());
    }

}
