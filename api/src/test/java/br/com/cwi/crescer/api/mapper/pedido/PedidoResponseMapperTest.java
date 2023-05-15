package br.com.cwi.crescer.api.mapper.pedido;

import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.controller.response.pedido.PedidoResponse;
import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.mapper.item.ItemArmarioResponseMapper;
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
public class PedidoResponseMapperTest {

    @InjectMocks
    private PedidoResponseMapper tested;

    @Mock
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    @Test
    public void deveMapearOPedidoResponse() {
        Pedido pedido = new Pedido();
        List<Item> itens = new ArrayList<>();
        Item item = new Item();
        itens.add(item);
        pedido.setItens(itens);
        Chat chat = new Chat();
        pedido.setChat(chat);
        ItemArmarioResponse itemArmarioResponse = new ItemArmarioResponse();

        Mockito.when(itemArmarioResponseMapper.apply(item)).thenReturn(itemArmarioResponse);
        PedidoResponse result = tested.apply(pedido);

        Mockito.verify(itemArmarioResponseMapper).apply(item);
        Assert.assertEquals(pedido.getIdPedido(), result.getIdPedido());
        Assert.assertEquals(pedido.getData(), result.getData());
        Assert.assertEquals(pedido.getIdPedido(), result.getIdPedido());
        Assert.assertEquals(pedido.isAtivo(), result.isAtivo());
    }
}
