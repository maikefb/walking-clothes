package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.pedido.PedidoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPedidoPorIdServiceTest {

    @InjectMocks
    private BuscarPedidoPorIdService tested;

    @Mock
    private PedidoRepository pedidoRepository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoPedidoNaoForEncontrado() {
        int id = 1;

        tested.find(id);

        Mockito.verify(pedidoRepository).findByIdPedido(id);
    }

    @Test
    public void deveRetornarOPedidoDoIdInformado() {
        int id = 1;
        Pedido pedido = new Pedido();

        Mockito.when(pedidoRepository.findByIdPedido(id)).thenReturn(Optional.of(pedido));
        Pedido result = tested.find(id);

        Mockito.verify(pedidoRepository).findByIdPedido(id);
        Assert.assertNotNull(result);
    }

}
