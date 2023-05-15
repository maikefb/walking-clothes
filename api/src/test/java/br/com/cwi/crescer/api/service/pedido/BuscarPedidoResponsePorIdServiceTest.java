package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.controller.response.pedido.PedidoResponse;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.mapper.pedido.PedidoResponseMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPedidoResponsePorIdServiceTest {

    @InjectMocks
    private BuscarPedidoResponsePorIdService tested;

    @Mock
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Mock
    private PedidoResponseMapper pedidoResponseMapper;

    @Test
    public void deveBuscarEMapearOPedidoParaResponse() {
        int id = 0;
        Pedido pedido = new Pedido();
        PedidoResponse pedidoResponse = new PedidoResponse();

        Mockito.when(buscarPedidoPorIdService.find(id)).thenReturn(pedido);
        Mockito.when(pedidoResponseMapper.apply(pedido)).thenReturn(pedidoResponse);
        PedidoResponse result = tested.find(id);

        Mockito.verify(buscarPedidoPorIdService).find(id);
        Mockito.verify(pedidoResponseMapper).apply(pedido);
        Assert.assertNotNull(result);
    }
}