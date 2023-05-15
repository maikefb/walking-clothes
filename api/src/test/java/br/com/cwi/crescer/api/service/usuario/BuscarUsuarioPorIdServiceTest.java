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
public class BuscarUsuarioPorIdServiceTest {

    @InjectMocks
    private BuscarUsuarioPorIdService tested;

    @Mock
    private UsuarioRepository repository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoRegistroNaoForEncontrado() {
        int idUsuario = 1;

        tested.apply(idUsuario);
        Mockito.verify(repository).findByIdUsuario(idUsuario);
    }

    @Test
    public void deveRetornarUsuarioQuandoForEncontrado() {
        int idUsuario = 1;
        Usuario usuario = new Usuario();

        Mockito.when(repository.findByIdUsuario(idUsuario)).thenReturn(Optional.of(usuario));
        Usuario result = tested.apply(idUsuario);

        Mockito.verify(repository).findByIdUsuario(idUsuario);
        Assert.notNull(result);
    }

}
