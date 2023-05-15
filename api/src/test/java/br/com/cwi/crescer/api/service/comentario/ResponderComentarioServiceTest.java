package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.controller.request.comentario.ComentarRequest;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.comentario.ComentarioRepository;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
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
public class ResponderComentarioServiceTest {

    @InjectMocks
    private ResponderComentarioService tested;

    @Mock
    private BuscarComentarioPorIdService comentarioPorIdService;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveResponderComentarioENotificarOAutorDoComentario() {
        ComentarRequest comentarRequest = new ComentarRequest();
        int id = 1;
        Usuario usuario = new Usuario();
        Usuario usuario1 = new Usuario();
        List<Notificacao> notificacoes = new ArrayList<>();
        usuario1.setNotificacoes(notificacoes);
        Comentario comentarioSendoRespondido = new Comentario();
        comentarioSendoRespondido.setIdUsuario(usuario1);
        List<Comentario> comentarios = new ArrayList<>();
        comentarioSendoRespondido.setComentarios(comentarios);

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(comentarioPorIdService.find(id)).thenReturn(comentarioSendoRespondido);
        Mockito.when(usuarioRepository.save(usuario1)).thenReturn(usuario1);
        tested.responder(comentarRequest, id);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(comentarioPorIdService).find(id);
        Mockito.verify(usuarioRepository).save(usuario1);
    }

}
