package br.com.cwi.crescer.api.service.notificacao;

import br.com.cwi.crescer.api.controller.response.notificacao.NotificacaoResponse;
import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.notificacao.NotificacaoReponseMapper;
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
public class BuscarNotificacaoServiceTest {

    @InjectMocks
    private BuscarNotificacaoService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private NotificacaoReponseMapper notificacaoResponseMapper;

    @Test
    public void deveLancarExcecaoQuandoNaoEncontrarNotificacao() {
        Usuario usuario = new Usuario();
        Notificacao notificacao = new Notificacao();
        NotificacaoResponse notificacaoResponse = new NotificacaoResponse();
        List<Notificacao> notificacoes = new ArrayList<>();
        notificacoes.add(notificacao);
        usuario.setNotificacoes(notificacoes);

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(notificacaoResponseMapper.apply(notificacao)).thenReturn(notificacaoResponse);
        tested.find();

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(notificacaoResponseMapper).apply(notificacao);
    }

}
