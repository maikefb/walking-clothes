package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.BuscarUsuarioMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarInfoPerfilServiceTest {

    @InjectMocks
    private BuscarInfoPerfilService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private BuscarUsuarioMapper buscarUsuarioMapper;

    @Test
    public void deveRetornarAsInformacoesDoUsuarioLogado() {
        BuscarUsuarioResponse buscarUsuarioResponse = new BuscarUsuarioResponse();
        Usuario usuario = new Usuario();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(buscarUsuarioMapper.apply(usuario)).thenReturn(buscarUsuarioResponse);
        BuscarUsuarioResponse result = tested.find();

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(buscarUsuarioMapper).apply(usuario);
        Assert.assertNotNull(result);
    }

}