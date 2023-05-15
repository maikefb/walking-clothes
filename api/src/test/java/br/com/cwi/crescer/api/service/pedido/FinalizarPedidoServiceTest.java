package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.domain.Status;
import br.com.cwi.crescer.api.repository.pedido.PedidoRepository;
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
public class FinalizarPedidoServiceTest {

    @InjectMocks
    private FinalizarPedidoService tested;

    @Mock
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Test
    public void deveDeixarOPedidoInativoESeusItensIndisponiveis() {
        int id = 0;
        Pedido pedido = new Pedido();
        List<Item> itens = new ArrayList<>();
        Item item = new Item();
        itens.add(item);
        pedido.setItens(itens);

        Mockito.when(buscarPedidoPorIdService.find(id)).thenReturn(pedido);
        Mockito.when(pedidoRepository.save(pedido)).thenReturn(pedido);
        tested.finalizar(id);

        Mockito.verify(buscarPedidoPorIdService).find(id);
        Mockito.verify(pedidoRepository).save(pedido);
        Assert.assertFalse(pedido.isAtivo());
        Assert.assertEquals(Status.I, pedido.getItens().get(0).getStatus());
    }

}
