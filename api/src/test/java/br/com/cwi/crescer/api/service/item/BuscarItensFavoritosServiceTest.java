package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.item.ItemArmarioResponseMapper;
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
public class BuscarItensFavoritosServiceTest {

    @InjectMocks
    private BuscarItensFavoritosService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    @Test
    public void deveBuscarItensFavoritosDoUsuario() {
        Usuario usuario = new Usuario();
        Item item = new Item();
        List<Item> favoritos = new ArrayList<>();
        favoritos.add(item);
        usuario.setFavoritos(favoritos);
        ItemArmarioResponse itemArmarioResponse = new ItemArmarioResponse();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(itemArmarioResponseMapper.apply(item)).thenReturn(itemArmarioResponse);
        tested.find();

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(itemArmarioResponseMapper).apply(item);
    }

}
