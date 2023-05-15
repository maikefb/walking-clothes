package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.PerfilUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExibirPerfilUsuarioLogadoServiceTest {

    @InjectMocks
    private ExibirPerfilUsuarioLogadoService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private ExibirPerfilUsuarioService exibirPerfilUsuarioService;

    @Test
    public void deveExibiriOPerfilDoUsuarioLogado() {
        Usuario usuario = new Usuario();
        int pagina = 1;
        PerfilUsuarioResponse perfilUsuarioResponse = new PerfilUsuarioResponse();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(exibirPerfilUsuarioService.apply(usuario.getIdUsuario(), pagina)).thenReturn(perfilUsuarioResponse);
        tested.apply(pagina);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(exibirPerfilUsuarioService).apply(usuario.getIdUsuario(), pagina);
    }
}
