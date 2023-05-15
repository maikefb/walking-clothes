package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.domain.Status;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
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
public class CancelarPedidoServiceTest {

    @InjectMocks
    private CancelarPedidoService tested;

    @Mock
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Test
    public void deveDeixarOPedidoInativoEDisponibilizarNovamenteOsItensENotificarOVendedor() {
        int id = 0;
        Usuario usuario = new Usuario();
        Pedido pedido = new Pedido();
        Item item = new Item();
        List<Item> itens = new ArrayList<>();
        itens.add(item);
        pedido.setItens(itens);
        Usuario vendedor = new Usuario();
        pedido.setVendedor(vendedor);
        int qtdNotificacoesEsperada = 1;

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(buscarPedidoPorIdService.find(id)).thenReturn(pedido);
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        tested.cancelar(id);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(buscarPedidoPorIdService).find(id);
        Mockito.verify(usuarioRepository).save(usuario);
        Assert.assertFalse(pedido.isAtivo());
        Assert.assertEquals(Status.D, item.getStatus());
        Assert.assertEquals(qtdNotificacoesEsperada, vendedor.getNotificacoes().size());
    }

}
