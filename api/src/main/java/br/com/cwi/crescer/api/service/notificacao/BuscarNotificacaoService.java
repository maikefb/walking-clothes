package br.com.cwi.crescer.api.service.notificacao;

import br.com.cwi.crescer.api.controller.response.notificacao.NotificacaoResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.notificacao.NotificacaoReponseMapper;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarNotificacaoService {
    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private NotificacaoReponseMapper notificacaoResponseMapper;

    public List<NotificacaoResponse> find() {
        Usuario usuario = usuarioLogadoService.get();
        return usuario.getNotificacoes().stream().map(notificacao -> notificacaoResponseMapper.apply(notificacao))
                .collect(Collectors.toList());
    }
}