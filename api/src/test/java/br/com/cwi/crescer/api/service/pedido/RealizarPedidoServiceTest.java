package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.controller.request.pedido.PedidoRequest;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import br.com.cwi.crescer.api.service.usuario.BuscarUsuarioPorIdService;
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
public class RealizarPedidoServiceTest {

    @InjectMocks
    private RealizarPedidoService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Test
    public void deveRealizarOPedidoDosItensSolicitados() {
        PedidoRequest request = new PedidoRequest();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        request.setIds(ids);
        Usuario usuario = new Usuario();
        Item item = new Item();
        Usuario vendedor = new Usuario();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(buscarItemPorIdService.find(request.getIds().get(0))).thenReturn(item);
        Mockito.when(buscarUsuarioPorIdService.apply(request.getIdVendedor())).thenReturn(vendedor);
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        tested.realizar(request);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(buscarItemPorIdService).find(request.getIds().get(0));
        Mockito.verify(buscarUsuarioPorIdService).apply(request.getIdVendedor());
        Mockito.verify(usuarioRepository).save(usuario);
        Assert.assertTrue(usuario.getPedidos().get(0).isAtivo());

    }

}
