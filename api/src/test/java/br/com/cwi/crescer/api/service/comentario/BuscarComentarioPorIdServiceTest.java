package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.comentario.ComentarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarComentarioPorIdServiceTest {

    @InjectMocks
    private BuscarComentarioPorIdService tested;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoComentarioNaoForEncontrado() {
        int id = 1;

        tested.find(id);

        Mockito.verify(comentarioRepository).findById(id);
    }

    @Test
    public void deveRetornarOComentarioEncontrado() {
        int id = 1;
        Comentario comentario = new Comentario();

        Mockito.when(comentarioRepository.findById(id)).thenReturn(Optional.of(comentario));
        Comentario result = tested.find(id);

        Mockito.verify(comentarioRepository).findById(id);
        Assert.assertNotNull(result);
    }

}
