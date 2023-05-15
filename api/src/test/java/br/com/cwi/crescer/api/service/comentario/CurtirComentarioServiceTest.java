package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Curtida;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.curtida.CurtidaRepository;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CurtirComentarioServiceTest {

    @InjectMocks
    private CurtirComentarioService tested;

    @Mock
    private BuscarComentarioPorIdService buscarComentarioPorIdService;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private CurtidaRepository repository;

    @Test
    public void deveCurtirOComentarioDoIdInformado() {
        int id = 0;
        Comentario comentario = new Comentario();
        comentario.setCurtidas(new ArrayList<>());
        Usuario usuario = new Usuario();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(repository.findCurtidaByUsuario(usuario, id)).thenReturn(Optional.empty());
        Mockito.when(buscarComentarioPorIdService.find(id)).thenReturn(comentario);
        tested.curtir(id);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(repository).findCurtidaByUsuario(usuario, id);
        Mockito.verify(buscarComentarioPorIdService).find(id);
    }

    @Test
    public void deveRemoverACurtidaDoComentarioDoIdInformadoQuandoElaJaExistir() {
        int id = 0;
        Usuario usuario = new Usuario();
        Curtida curtida = new Curtida();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(repository.findCurtidaByUsuario(usuario, id)).thenReturn(Optional.of(curtida));
        tested.curtir(id);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(repository).findCurtidaByUsuario(usuario, id);
        Mockito.verify(repository).delete(curtida);
    }
}
