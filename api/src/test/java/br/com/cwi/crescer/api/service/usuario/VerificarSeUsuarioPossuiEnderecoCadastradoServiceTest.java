package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VerificarSeUsuarioPossuiEnderecoCadastradoServiceTest {

    @InjectMocks
    private VerificarSeUsuarioPossuiEnderecoCadastradoService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Test
    public void deveVerificarSeOUsuarioPossuiUmEnderecoCadastrado() {
        Usuario usuario = new Usuario();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        tested.apply();

        Mockito.verify(usuarioLogadoService).get();
    }
}
