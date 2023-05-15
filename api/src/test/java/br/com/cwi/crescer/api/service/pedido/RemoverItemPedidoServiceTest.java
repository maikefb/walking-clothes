package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.repository.pedido.PedidoRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
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
public class RemoverItemPedidoServiceTest {

    @InjectMocks
    private RemoverItemPedidoService tested;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Mock
    private CancelarPedidoService cancelarPedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Test
    public void deveRemoverOItemSolicitadoDoPedido() {
        int idItem = 0;
        int idPedido = 0;
        Item item = new Item();
        Item item2 = new Item();
        Pedido pedido = new Pedido();
        List<Item> itens = new ArrayList<>();
        itens.add(item);
        itens.add(item2);
        pedido.setItens(itens);

        Mockito.when(buscarItemPorIdService.find(idItem)).thenReturn(item);
        Mockito.when(buscarPedidoPorIdService.find(idPedido)).thenReturn(pedido);
        Mockito.when(pedidoRepository.save(pedido)).thenReturn(pedido);
        tested.remover(idItem, idPedido);

        Mockito.verify(buscarItemPorIdService).find(idItem);
        Mockito.verify(buscarPedidoPorIdService).find(idPedido);
        Mockito.verify(pedidoRepository).save(pedido);
        Assert.assertNotSame(item, pedido.getItens().get(0));
    }

    @Test
    public void deveCancelarOPedidoQuandoNaoRestaremItensAposARemocaoDoItemSolicitado() {
        int idItem = 0;
        int idPedido = 0;
        Item item = new Item();
        Pedido pedido = new Pedido();
        int qtdItensEsperada = 0;

        Mockito.when(buscarItemPorIdService.find(idItem)).thenReturn(item);
        Mockito.when(buscarPedidoPorIdService.find(idPedido)).thenReturn(pedido);
        tested.remover(idItem, idPedido);

        Mockito.verify(buscarItemPorIdService).find(idItem);
        Mockito.verify(buscarPedidoPorIdService).find(idPedido);
        Mockito.verify(cancelarPedidoService).cancelar(idPedido);
        Assert.assertEquals(qtdItensEsperada, pedido.getItens().size());
        Assert.assertFalse(pedido.isAtivo());
    }

}
