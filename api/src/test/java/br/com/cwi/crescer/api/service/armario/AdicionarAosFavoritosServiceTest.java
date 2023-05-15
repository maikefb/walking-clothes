package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdicionarAosFavoritosServiceTest {

    @InjectMocks
    private AdicionarAosFavoritosService tested;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;


    @Test
    public void deveAdicionarItemAoArmarioDosFavoritos() {
        int id = 0;
        Item item = new Item();
        Usuario usuario = new Usuario();
        int esperado = 1;

        Mockito.when(buscarItemPorIdService.find(id)).thenReturn(item);
        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        tested.add(id);

        Mockito.verify(buscarItemPorIdService).find(id);
        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(usuarioRepository).save(usuario);
        Assert.assertEquals(esperado, usuario.getFavoritos().size());
    }

    @Test
    public void deveRemoverItemDosFavoritosQuandoJaEstiverFavoritado() {
        int id = 0;
        Item item = new Item();
        Usuario usuario = new Usuario();
        usuario.getFavoritos().add(item);
        int esperado = 0;

        Mockito.when(buscarItemPorIdService.find(id)).thenReturn(item);
        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        tested.add(id);

        Mockito.verify(buscarItemPorIdService).find(id);
        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(usuarioRepository).save(usuario);
        Assert.assertEquals(esperado, usuario.getFavoritos().size());
    }

}
