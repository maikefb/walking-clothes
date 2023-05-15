package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VerificarItemFavoritoServiceTest {

    @InjectMocks
    private VerificarItemFavoritoService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Test
    public void deveRetornarTrueSeOItemFoiFavoritado() {
        Item item = new Item();
        Usuario usuario = new Usuario();
        usuario.getFavoritos().add(item);

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        boolean result = tested.apply(item);

        Mockito.verify(usuarioLogadoService).get();
        Assert.assertTrue(result);
    }

    @Test
    public void deveRetornarFalseSeOItemNaoFoiFavoritado() {
        Item item = new Item();
        Usuario usuario = new Usuario();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        boolean result = tested.apply(item);

        Mockito.verify(usuarioLogadoService).get();
        Assert.assertFalse(result);
    }
}