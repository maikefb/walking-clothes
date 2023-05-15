package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioPorEmailServiceTest {

    @InjectMocks
    private BuscarUsuarioPorEmailService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        String email = "";

        tested.buscar(email);

        Mockito.verify(usuarioRepository).findByEmail(email);
    }

    @Test
    public void deveRetornarUsuarioQuandoForEncontrado() {
        String email = "";
        Usuario usuario = new Usuario();

        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        Usuario result = tested.buscar(email);

        Mockito.verify(usuarioRepository).findByEmail(email);
        Assert.notNull(result);
    }

}