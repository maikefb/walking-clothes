package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.controller.response.pedido.PedidoResponse;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.pedido.PedidoResponseMapper;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPedidoServiceTest {

    @InjectMocks
    private BuscarPedidoService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private PedidoResponseMapper pedidoResponseMapper;

    @Test
    public void deveRetornarListaDePedidosResponse() {
        Usuario usuario = new Usuario();
        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido();
        pedidos.add(pedido);
        usuario.setPedidos(pedidos);
        PedidoResponse pedidoResponse = new PedidoResponse();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(pedidoResponseMapper.apply(pedido)).thenReturn(pedidoResponse);
        tested.get();

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(pedidoResponseMapper).apply(pedido);
    }

}
